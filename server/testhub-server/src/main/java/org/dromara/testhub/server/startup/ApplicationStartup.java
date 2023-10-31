package org.dromara.testhub.server.startup;

import cn.hutool.core.collection.CollectionUtil;
import com.goddess.nsrule.core.executer.mode.base.action.Action;
import org.dromara.testhub.sdk.model.rule.TestHubAction;
import org.dromara.testhub.server.domain.dto.res.rule.*;
import org.dromara.testhub.server.infrastructure.repository.po.EnvironmentPo;
import org.dromara.testhub.server.infrastructure.repository.po.RulePo;
import org.dromara.testhub.server.infrastructure.repository.po.TreeInfoPo;
import com.goddess.nsrule.core.executer.context.RuleConfig;
import com.goddess.nsrule.core.executer.context.RuleProject;
import com.goddess.nsrule.core.executer.mode.Rule;
import com.goddess.nsrule.core.executer.mode.base.action.Param;
import org.dromara.testhub.server.core.rule.DbManager;
import org.dromara.testhub.server.core.rule.DbRuleManager;
import org.dromara.testhub.server.core.util.TreeUtil;
import org.dromara.testhub.server.domain.convert.RuleConvertor;
import org.dromara.testhub.sdk.dto.res.TreeNodeResDto;
import org.dromara.testhub.server.core.rule.CacheManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private RuleConfig config;
    @Autowired
    private DbRuleManager dbRuleManager;
    @Autowired
    private DbManager dbManager;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        List<EnvironmentPo> environmentPos = dbRuleManager.getEnvironmentPos();
        Map<Long, List<EnvironmentPo>> environmentPoMap = CollectionUtil.isEmpty(environmentPos) ? new HashMap<>() : environmentPos.stream().collect(Collectors.groupingBy(EnvironmentPo::getProjectId));

        for (String key : config.getProjectMap().keySet()) {
            RuleProject ruleProject = config.getProjectMap().get(key);

            List<RulePo> rulePos = dbRuleManager.getRulePos(key);
            for(RulePo rulePo:rulePos){
                CacheManager.addRulePo(rulePo);
            }

            RuleProjectResDto projectResDto = new RuleProjectResDto();
            projectResDto.setCode(ruleProject.getCode());
            projectResDto.setName(ruleProject.getName());


            List<TreeInfoPo> treeInfoPos = dbManager.getTreeInfos(key+"_CASE");
            List<TreeNodeResDto> treeNodeList = TreeUtil.convert(treeInfoPos);
            Map<String, TreeNodeResDto> treeNodeMap = TreeUtil.buildTreeNodeMap(treeNodeList);
            if(ruleProject.getRules().size()>0){
                for (Rule rule : ruleProject.getRules()) {
                    RulePo rulePo = CacheManager.getRulePo(rule.getId());
//                    File file = new File(rule.getSource());
//                    CacheManager.addFile(rule.getCode(), file);
                    RuleResDto resDto = RuleConvertor.ruleModelAll2Res(rule,rulePo);
                    resDto.setFileContent(rulePo.getFileContent());
                    resDto.setTreeId(rulePo.getTreeId());
//                    resDto.setFileContent(FileUtil.readUtf8String(file));
                    CacheManager.putRule(resDto);
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

            //项目环境设置
            List<RuleEnvironmentResDto> environments = new ArrayList<>();
            for (EnvironmentPo environmentPo : environmentPoMap.getOrDefault(ruleProject.getId(),new ArrayList<>())) {
                RuleEnvironmentResDto environment = new RuleEnvironmentResDto();
                List<RuleParamResDto> paramResDtos = new ArrayList<>();
                List<Param> params = ruleProject.getGlobalParams(environmentPo.getCode());
                for(Param param:params){
                    RuleParamResDto ruleParamResDto = RuleConvertor.paramModel2Res(param);
                    paramResDtos.add(ruleParamResDto);
                }
                environment.setId(environmentPo.getId());
                environment.setCode(environmentPo.getCode());
                environment.setName(environmentPo.getName());
                environment.setRemark(environmentPo.getRemark());
                environment.setParams(paramResDtos);
                environments.add(environment);
            }
            projectResDto.setEnvironments(environments);

            //项目级行为
            List<RuleActionResDto> actionResDtos = new ArrayList<>();
            for (Action action : ruleProject.getActions()) {
                RuleActionResDto actionResDto = RuleConvertor.ruleActionModel2Res((TestHubAction) action);
                actionResDtos.add(actionResDto);
            }
            projectResDto.setActions(actionResDtos);
            CacheManager.putProject(projectResDto);
        }
    }

}
