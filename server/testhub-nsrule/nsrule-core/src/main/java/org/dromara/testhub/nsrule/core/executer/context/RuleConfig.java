package org.dromara.testhub.nsrule.core.executer.context;

import org.dromara.testhub.nsrule.core.constant.ConstantUtil;
import org.dromara.testhub.nsrule.core.constant.RuleException;
import org.dromara.testhub.nsrule.core.executer.meta.MetaClass;
import org.dromara.testhub.nsrule.core.executer.mode.Rule;
import org.dromara.testhub.nsrule.core.executer.mode.base.action.Action;
import org.dromara.testhub.nsrule.core.executer.mode.base.function.FunctionHandlerFactory;
import org.dromara.testhub.nsrule.core.executer.operation.OperationFactory;
import org.dromara.testhub.nsrule.core.expand.FormulaBuilder;
import org.dromara.testhub.nsrule.core.expand.impl.DefaultFormulaBuilder;
import org.dromara.testhub.nsrule.core.parser.ActionParser;
import org.dromara.testhub.nsrule.core.parser.ExecuteParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

/**
 * 配置中心
 *
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/4 19:14
 */
public class RuleConfig {

    private static RuleConfig INSTANCE= null;

    //流程定义资源地址
    private String rulePath;

    //行为解析器
    private ActionParser actionParser;
    //执行解析器
    private ExecuteParser executeParser;
    //公式解析器
    private FormulaBuilder formulaBuilder;

    //操作符工厂
    private OperationFactory relationFactory;
    //扩展方法工厂
    private FunctionHandlerFactory functionHandlerFactory;

    //工程
    private Map<String, RuleProject> projectMap = new ConcurrentHashMap<>();

    //规则缓存
    private Map<String, Rule> ruleMap = new ConcurrentHashMap<>();

    private List<Action> actions = new ArrayList<>();


    private RuleConfig() {
        relationFactory = OperationFactory.getInstance();
        formulaBuilder = new DefaultFormulaBuilder();
        functionHandlerFactory = FunctionHandlerFactory.getInstance();
    }

    public static RuleConfig getInstance() {
        if (INSTANCE != null) {
            return INSTANCE;
        } else {
            synchronized (RuleConfig.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RuleConfig();
                }
            }
        }
        return INSTANCE;
    }
    public void addRule(Rule rule){
        if(ruleMap.containsKey(rule.getCode())){
            throw new RuleException("规则编码重复" + rule.getCode());
        }
        ruleMap.put(rule.getCode(), rule);
    }
    public void updateRule(Rule rule){
        ruleMap.put(rule.getCode(), rule);
        this.projectMap.get(rule.getProject()).updateRule(rule);
    }
    public Rule getRule(String code){
        Rule rule = ruleMap.get(code);
        if(rule == null){
            throw new RuleException("规则编码不存在" + code);
        }
        return rule;
    }
    public Rule getRule(String code,boolean canNull){
        if(canNull){
            return ruleMap.get(code);
        }else {
            return getRule(code);
        }

    }


    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public String getRulePath() {
        return rulePath;
    }

    public void setRulePath(String rulePath) {
        this.rulePath = rulePath;
    }

    public void setProjectMap(List<RuleProject> projects) {
        for (RuleProject project : projects) {
            if (this.projectMap.containsKey(project.getCode())) {
                throw new RuleException("项目编码重复" + project.getCode());
            }
            this.projectMap.put(project.getCode(), project);
        }
    }
    public List<Rule> getRules(){
        return ruleMap.values().stream().collect(Collectors.toList());
    }

    public Context buildContext(String ruleCode, Executor executor) {
        Rule rule = getRule(ruleCode);
        RuleProject ruleProject = projectMap.get(rule.getProject());
        return ruleProject.buildContext(ruleCode, null, executor);
    }

    public Map<String, RuleProject> getProjectMap() {
        return projectMap;
    }
    public RuleProject getProject(String code) {
        return projectMap.get(code);
    }

    public OperationFactory getRelationFactory() {
        return relationFactory;
    }

    public void setRelationFactory(OperationFactory relationFactory) {
        this.relationFactory = relationFactory;
    }

    public FormulaBuilder getFormulaBuilder() {
        return formulaBuilder;
    }

    public void setFormulaBuilder(FormulaBuilder formulaBuilder) {
        this.formulaBuilder = formulaBuilder;
    }

    public FunctionHandlerFactory getFunctionHandlerFactory() {
        return functionHandlerFactory;
    }

    public void setFunctionHandlerFactory(FunctionHandlerFactory functionHandlerFactory) {
        this.functionHandlerFactory = functionHandlerFactory;
    }

    public ActionParser getActionParser() {
        return actionParser;
    }

    public void setActionParser(ActionParser actionParser) {
        this.actionParser = actionParser;
    }

    public ExecuteParser getExecuteParser() {
        return executeParser;
    }

    public void setExecuteParser(ExecuteParser executeParser) {
        this.executeParser = executeParser;
    }

    public boolean isInnerDataType(String dataType) {
        return ConstantUtil.isInnerDataType(dataType);
    }

}
