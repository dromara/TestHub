package org.dromara.testhub.server.core.rule;

import cn.hutool.core.collection.CollectionUtil;
//import com.goddess.testhub.application.dto.res.ExecuteResult.ExecutionResult;
//import com.goddess.testhub.application.dto.res.rule.RuleActionResDto;
import org.dromara.testhub.server.core.util.TreeUtil;
import org.dromara.testhub.server.domain.dto.res.ExecuteResult.ExecutionResult;
import org.dromara.testhub.sdk.dto.res.TreeNodeResDto;
import org.dromara.testhub.server.domain.dto.res.rule.RuleProjectResDto;
import org.dromara.testhub.server.domain.dto.res.rule.RuleProjectSimpleResDto;
import org.dromara.testhub.server.domain.dto.res.rule.RuleResDto;
import org.dromara.testhub.server.infrastructure.repository.po.RulePo;
import org.dromara.testhub.server.infrastructure.repository.po.TreeInfoPo;
import com.goddess.nsrule.core.executer.context.RuleConfig;
import com.goddess.nsrule.core.executer.context.RuleProject;
import com.goddess.nsrule.core.executer.mode.Rule;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

@Component
public class CacheManager {
    //执行结果
    private static List<ExecutionResult> RESULTS = new ArrayList<>();

    public List<ExecutionResult> getExecutionResult() {
        return RESULTS;
    }

    public static synchronized void addResult(ExecutionResult executionResult) {
        RESULTS.add(executionResult);
    }

    public static synchronized void delResult(List<String> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return;
        }
        for (int i = RESULTS.size() - 1; i > -1; i--) {
            if (ids.contains(RESULTS.get(i).getId())) {
                RESULTS.remove(i);
            }
        }
    }


    //规则文件
    private static Map<String, File> ruleLastTimeMap = new HashMap<>();

    public static void addFile(String ruleCode, File file) {
        ruleLastTimeMap.put(ruleCode, file);
    }

    public static File getFile(String ruleCode) {
        return ruleLastTimeMap.get(ruleCode);
    }

    //规则Db
    private static Map<Long, RulePo> rulePoMap = new HashMap<>();

    public static synchronized void addRulePo(RulePo rulePo) {
        rulePoMap.put(rulePo.getId(), rulePo);
    }

    public static synchronized RulePo getRulePo(Long ruleId) {
        return rulePoMap.get(ruleId);
    }


    //规则
    private static Map<String, RuleResDto> RULE_MAP = new ConcurrentHashMap<>();

    public static synchronized void putRule(RuleResDto resDto) {
        RuleResDto old = RULE_MAP.get(resDto.getCode());
        if(old == resDto){
            return;
        }
        RULE_MAP.put(resDto.getCode(), resDto);
        if(old==null){
            puProjectRules(resDto);
        }else {
            RULE_MAP.put(resDto.getCode(), resDto);
            puProjectRules(resDto);
        }

    }

    public static synchronized RuleResDto getRule(String code) {
        return RULE_MAP.get(code);
    }

    public static synchronized List<RuleResDto> getAllRule() {
        Collection<RuleResDto> values = RULE_MAP.values();
        return new ArrayList<>(values);
    }


    //项目用例列表
    private static Map<String, List<RuleResDto>> PROJECT_RULE_MAP = new ConcurrentHashMap<>();

    private synchronized static void puProjectRules(RuleResDto ruleResDto){
        if(PROJECT_RULE_MAP.get(ruleResDto.getProject())==null){
            List<RuleResDto> rules = new ArrayList<>();
            rules.add(ruleResDto);
            PROJECT_RULE_MAP.put(ruleResDto.getProject(),rules);
        }else {
            List<RuleResDto> rules =  PROJECT_RULE_MAP.get(ruleResDto.getProject());
            OptionalInt index = IntStream.range(0, rules.size())
                    .filter(i -> rules.get(i).getCode() == ruleResDto.getCode())
                    .findFirst();
            if (index.isPresent()) {
                rules.add(index.getAsInt(),ruleResDto);
            } else {
                rules.add(ruleResDto);
            }
        }
    }

    public static synchronized List<RuleResDto> getProjectRules(String code){
        return PROJECT_RULE_MAP.get(code);
    }

    //项目
    private static Map<String, RuleProjectResDto> PROJECT_MAP = new ConcurrentHashMap<>();


    public static synchronized void putProject(RuleProjectResDto resDto) {
        PROJECT_MAP.put(resDto.getCode(), resDto);
    }

    public static synchronized RuleProjectResDto getProject(String code) {
        return PROJECT_MAP.get(code);
    }

    public static synchronized List<RuleProjectResDto> getAllProject() {
        Collection<RuleProjectResDto> values = PROJECT_MAP.values();
        return new ArrayList<>(values);
    }
    public static synchronized List<RuleProjectSimpleResDto> getAllProjectSimple() {
        List<RuleProjectSimpleResDto> resDtos = new ArrayList<>();
        for (String key:PROJECT_MAP.keySet()) {
            RuleProjectResDto project = PROJECT_MAP.get(key);
            resDtos.add(new RuleProjectSimpleResDto(project.getId(),project.getCode(),project.getName()));
        }
        return resDtos;
    }

    public static synchronized void putProjectRule(RuleResDto ruleResDto) {
        //还不支持添加
        RuleProjectResDto projectResDto = getProject(ruleResDto.getProject());
        replaceTree(ruleResDto,projectResDto.getRuleTrees());
    }
    private static synchronized void replaceTree(RuleResDto ruleResDto,List<TreeNodeResDto> ruleTrees){
        if(CollectionUtil.isEmpty(ruleTrees)){
            return;
        }
        for (TreeNodeResDto treeNode:ruleTrees){
            if(treeNode.getKey().equals(ruleResDto.getCode())){
                //找到了
                treeNode.setName(ruleResDto.getName());
                treeNode.setData(ruleResDto);
            }else {
                replaceTree(ruleResDto,treeNode.getChildren());
            }
        }
    }


    //重新建立类目树
    public static synchronized void reBuildTreeNode(DbManager dbManager, RuleConfig config, String projectCode) {
        RuleProjectResDto projectResDto = getProject(projectCode);
        RuleProject ruleProject = config.getProjectMap().get(projectCode);
        List<TreeInfoPo> treeInfoPos = dbManager.getTreeInfos(projectCode+"_CASE");
        List<TreeNodeResDto> treeNodeList = TreeUtil.convert(treeInfoPos);
        Map<String, TreeNodeResDto> treeNodeMap = TreeUtil.buildTreeNodeMap(treeNodeList);
        if(ruleProject.getRules().size()>0){
            for (Rule rule : ruleProject.getRules()) {
                RulePo rulePo = CacheManager.getRulePo(rule.getId());
                RuleResDto resDto = CacheManager.getRule(rule.getCode());

                TreeNodeResDto node = new TreeNodeResDto();
                node.setKey(resDto.getCode());
                node.setName(resDto.getName());
                node.setLeaf(true);
                node.setNodeType("CASE");
                node.setData(resDto);
                if(rulePo.getTreeId()!=0){
                    TreeNodeResDto parent = treeNodeMap.get(rulePo.getTreeId()+"");
                    parent.getChildren().add(node);
                }else {
                    treeNodeList.add(node);
                }
            }
        }
        projectResDto.setRuleTrees(treeNodeList);
    }
}
