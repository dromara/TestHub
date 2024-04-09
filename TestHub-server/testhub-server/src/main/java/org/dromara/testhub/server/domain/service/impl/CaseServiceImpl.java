package org.dromara.testhub.server.domain.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dromara.testhub.framework.exception.TestHubException;
import org.dromara.testhub.framework.util.FileUtil;
import org.dromara.testhub.framework.util.IdGenerator;
import org.dromara.testhub.nsrule.core.executer.context.RuleConfig;
import org.dromara.testhub.nsrule.core.executer.mode.Rule;
import org.dromara.testhub.nsrule.core.parser.RuleParser;
import org.dromara.testhub.nsrule.flow.model.Flow;
import org.dromara.testhub.nsrule.flow.model.FlowContext;
import org.dromara.testhub.nsrule.flow.model.RuleFlow;
import org.dromara.testhub.nsrule.parserXml.XMLRuleParser;
import org.dromara.testhub.sdk.action.dto.res.TreeNodeDataResDto;
import org.dromara.testhub.sdk.action.dto.res.TreeNodeResDto2;
import org.dromara.testhub.server.core.rule.CacheManager;
import org.dromara.testhub.server.core.rule.DbManager;
import org.dromara.testhub.server.core.rule.DbRuleManager;
import org.dromara.testhub.server.domain.convert.RuleConvertor;
import org.dromara.testhub.server.domain.dto.req.other.RenameDto;
import org.dromara.testhub.server.domain.dto.req.rule.ExecutionXmlReqDto;
import org.dromara.testhub.server.domain.dto.req.rule.RuleDocumentReqDto;
import org.dromara.testhub.server.domain.dto.res.ExecuteResult.ExecutionResult;
import org.dromara.testhub.server.domain.dto.res.rule.RuleResDto;
import org.dromara.testhub.server.domain.service.CaseService;
import org.dromara.testhub.server.domain.util.CaseTreeUtil;
import org.dromara.testhub.server.domain.util.CaseUtil;
import org.dromara.testhub.server.infrastructure.repository.dao.RuleMapper;
import org.dromara.testhub.server.infrastructure.repository.po.RulePo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author vinc
 */
@Slf4j
@Service("caseService")
public class CaseServiceImpl implements CaseService {
    @Autowired
    private DbManager dbManager;
    @Autowired
    private DbRuleManager dbRuleManager;
    @Autowired
    private RuleConfig ruleConfig;
    @Autowired
    private RuleMapper ruleMapper;
    @Autowired
    private IdGenerator idGenerator;

    @Override
    @Transactional
    public TreeNodeResDto2 rename(RenameDto renameDto){
        Rule rule = ruleConfig.getRule(renameDto.getKey());
        RulePo rulePo = ruleMapper.selectById(rule.getId());
        rule.setName(renameDto.getName());
        CacheManager.getProject(rulePo.getProjectCode()).getRuleTrees();
        //todo 先这样写吧

        rulePo.setName(renameDto.getName());

        TreeNodeResDto2 treeNodeResDto2 = new TreeNodeResDto2();
        treeNodeResDto2.setIsFolder(false);
        TreeNodeDataResDto nodeDataResDto = new TreeNodeDataResDto();
        nodeDataResDto.setKey(rulePo.getCode());
        nodeDataResDto.setName(renameDto.getName());
        nodeDataResDto.setNodeType(CaseTreeUtil.TYPE_CASE);
        nodeDataResDto.setParentKey(rulePo.getTreeId()+"");
        treeNodeResDto2.setData(nodeDataResDto);
        treeNodeResDto2.setIndex(rulePo.getCode());
        ruleMapper.updateById(rulePo);
        return treeNodeResDto2;
    }

    @Override
    public RuleResDto getOne(String code) {
        Rule rule = ruleConfig.getRule(code);
        return CacheManager.getRule(code);
    }

    @Override
    public String formatXml(String documentStr) {
        return FileUtil.formatXml(CaseUtil.getDocument(documentStr));
    }

    @Override
    public RuleResDto parserXml(RuleDocumentReqDto documentReqDto) {
        RuleParser<Document> parser = XMLRuleParser.getInstance(ruleConfig);
        Rule rule = null;
        try {
            Document document = CaseUtil.getDocument(documentReqDto);
            rule = parser.parse(document, ruleConfig);
        } catch (Exception e) {
            throw new TestHubException(e);
        }
        return RuleConvertor.ruleModelAll2Res(rule);
    }

    @Override
    @Transactional
    public RuleResDto saveRuleDocument(RuleDocumentReqDto documentReqDto, String model) {
        Document document = CaseUtil.getDocument(documentReqDto);
        return saveDocument(document, documentReqDto.getCode(), documentReqDto.getTreeId(), model);
    }



    //保存规则对象
    private synchronized RuleResDto saveDocument(Document document, String ruleCode, long treeId, String model) {

        RuleParser<Document> parser = XMLRuleParser.getInstance(ruleConfig);
        Rule rule = null;
        try {
            rule = parser.parse(document, ruleConfig);
        } catch (Exception e) {
            throw new TestHubException(e);
        }
        if (StringUtils.isNotEmpty(ruleCode)) {
            rule.setCode(ruleCode);
        }

        String fileContent = FileUtil.formatXml(document);
        Rule oldRule = ruleConfig.getRule(rule.getCode(), true);
        if ("save".equalsIgnoreCase(model)) {
            if (oldRule != null) {
                throw new TestHubException(rule.getCode() + "规则编码已经存在");
            }
        } else {
            RulePo oldRulePo = CacheManager.getRulePo(oldRule.getId());
            if (oldRulePo.getFileContent().equals(fileContent)) {
                //文本没有变化
                return CacheManager.getRule(oldRulePo.getCode());
            }
        }
        //设置ID
        if (oldRule != null) {
            rule.setId(oldRule.getId());
        } else {
            rule.setId(idGenerator.snowflakeId());
        }
        dbRuleManager.setIdRuleInfo((RuleFlow) rule);

        //更新 或 保存
        RulePo rulePo = dbRuleManager.saveRule(ruleConfig.getProject(rule.getProject()), (RuleFlow) oldRule, (RuleFlow) rule, fileContent, treeId);
        CacheManager.addRulePo(rulePo);

        RuleResDto ruleResDto = RuleConvertor.ruleModelAll2Res(rule,rulePo);
        ruleResDto.setTreeId(rulePo.getTreeId());
        ruleResDto.setFileContent(FileUtil.formatXml(document));
        CacheManager.putRule(ruleResDto);
        CacheManager.putProjectRule(ruleResDto);
        ruleConfig.updateRule(rule);
        CacheManager.reBuildTreeNode(dbManager, ruleConfig, rulePo.getProjectCode());
        return ruleResDto;
    }
}

