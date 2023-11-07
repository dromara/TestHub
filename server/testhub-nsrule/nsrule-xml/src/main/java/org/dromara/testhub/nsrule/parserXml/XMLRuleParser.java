package org.dromara.testhub.nsrule.parserXml;

import cn.hutool.core.collection.CollectionUtil;
import org.dromara.testhub.nsrule.core.constant.Constant;
import org.dromara.testhub.nsrule.core.constant.RuleException;
import org.dromara.testhub.nsrule.core.executer.context.RuleConfig;
import org.dromara.testhub.nsrule.core.executer.context.RuleProject;
import org.dromara.testhub.nsrule.core.executer.meta.MetaEnum;
import org.dromara.testhub.nsrule.core.executer.mode.base.action.Action;
import org.dromara.testhub.nsrule.core.executer.mode.base.action.Execute;
import org.dromara.testhub.nsrule.core.executer.mode.Rule;
import org.dromara.testhub.nsrule.graph.model.*;
import org.dromara.testhub.nsrule.model.Flow;
import org.dromara.testhub.nsrule.model.RuleFlow;
import org.dromara.testhub.nsrule.core.executer.mode.ruleLine.Expression;
import org.dromara.testhub.nsrule.core.parser.RuleParser;
import org.dromara.testhub.nsrule.core.util.DataCheckUtil;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/4 12:26
 */
public class XMLRuleParser implements RuleParser<Document> {

    private static RuleConfig ruleConfig;

    private static XMLRuleParser instance = null;

    private XMLRuleParser(RuleConfig ruleConfig) {
        XMLRuleParser.ruleConfig = ruleConfig;
    }

    public static XMLRuleParser getInstance(RuleConfig ruleConfig) {
        if (instance != null) {
            return instance;
        } else {
            synchronized (XMLRuleParser.class) {
                if (instance == null) {
                    instance = new XMLRuleParser(ruleConfig);
                }
            }
        }
        return instance;
    }

    @Override
    public Rule parse(Document document, RuleConfig ruleConfig) throws Exception {
        Rule rule = null;
        //根结点必须要是 rule
        if (document.getRootElement().getName().equals("rule")) {
            String code, name, model,project;
            code = document.getRootElement().attributeValue("code");
            name = document.getRootElement().attributeValue("name");
            model = document.getRootElement().attributeValue("model");
            project = document.getRootElement().attributeValue("project");
            if(ruleConfig.getProjectMap().keySet().size()>1){
                DataCheckUtil.notBlank(new String[]{code, model,project}, new String[]{"规则编码", "规则模式", "规则"+code+"的所属项目"});
            }else {
                DataCheckUtil.notBlank(new String[]{code, model}, new String[]{"规则编码", "规则模式"});
                project = ruleConfig.getProjectMap().values().stream().findFirst().get().getCode();
            }


            RuleProject ruleProject = ruleConfig.getProjectMap().get(project);
            if (Constant.RuleModel.FLOW.equalsIgnoreCase(model)) {
                RuleFlow ruleFlow = new RuleFlow();
                rule = ruleFlow;
            } else if (Constant.RuleModel.GRAPH.equalsIgnoreCase(model)) {
                RuleGraph ruleGraph = new RuleGraph();
                rule = ruleGraph;
            } else {
                throw new RuleException("不支持模式:" + model);
            }


            if(ruleProject == null){
                throw new RuleException("规则:"+code+"的所属项目" + project+"不存在");
            }
            rule.setCode(code);
            rule.setName(name);
            rule.setModel(model);
            rule.setProject(project);

            rule.setParams(XMLRuleConfigBuilder.parseParams(document.getRootElement().element("params")));
            List<MetaEnum> metaEnums = XMLRuleConfigBuilder.parseMetaEnums(document.getRootElement().element("metaEnums"));
            rule.setMetaEnums(metaEnums);
            rule.setMetaClasses(XMLRuleConfigBuilder.parseMetaClasses(document.getRootElement().element("metaClasses"), metaEnums));
            List<Action> actions = XMLRuleConfigBuilder.parseActions(document.getRootElement().element("actions"), ruleProject, rule.getMetaClassMap(), rule.getCode());
            rule.setActions(actions);
            Map<String, Action> actionMap = new HashMap<>();
            for (Action action : ruleProject.getActions()) {
                actionMap.put(action.getCode(), action);
            }
            for (Action action : actions) {
                actionMap.put(action.getCode(), action);
            }
            if (Constant.RuleModel.FLOW.equalsIgnoreCase(model)) {
                ((RuleFlow) rule).setFlows(parseFlows(document.getRootElement().element("flows"), actionMap, rule.getCode()));
            } else if (Constant.RuleModel.GRAPH.equalsIgnoreCase(model)) {
                RuleGraph ruleGraph = (RuleGraph) rule;
                ruleGraph.setGraphs(parseGraphs(document.getRootElement().element("graphs")));
                ruleGraph.setResults(parseResults(document.getRootElement().element("results")));
                rule = ruleGraph;
            }
        }
        return rule;
    }

    private List<Flow> parseFlows(Element element, Map<String, Action> actionMap, String msg) {
        List<Flow> flows = new ArrayList<>();
        List<Element> items = element.elements("flow");
        for (Element item : items) {
            Flow flow = new Flow();
            String code, name;
            code = item.attributeValue("code");
            name = item.attributeValue("name");
            DataCheckUtil.notBlank(new String[]{code}, new String[]{"流编码"});
            flow.setCode(code);
            flow.setName(name);
            flow.setExecutes(parseExecutes(item, actionMap, msg + ":" + code));
            if(CollectionUtil.isEmpty(flow.getExecutes())){
                throw new RuleException(code + ":流程中至少需要一个执行步骤");
            }
            flows.add(flow);
        }
        if (flows.size() != flows.stream().map(o -> o.getCode()).distinct().collect(Collectors.toList()).size()) {
            throw new RuleException(msg + ":流程编码不能重复");
        }
        return flows;
    }

    /**
     * 处理决策图节点
     *
     * @param element
     * @return
     */
    private List<Graph> parseGraphs(Element element) {
        List<Graph> graphs = new ArrayList<>();
        List<Element> items = element.elements("graph");
        for (Element item : items) {
            Graph graph = new Graph();
            String code, name, firstBranchCode;
            code = item.attributeValue("code");
            name = item.attributeValue("name");
            firstBranchCode = item.attributeValue("firstBranchCode");
            List<Branch> branches = parseBranches(item.element("branches"));
            graph.setCode(code);
            graph.setName(name);
            graph.setFirstBranchCode(firstBranchCode);
            graph.setBranches(branches);
            graphs.add(graph);
        }
        return graphs;
    }

    /**
     * 处理分支节点
     *
     * @param element
     * @return
     */
    private List<Branch> parseBranches(Element element) {
        List<Branch> branches = new ArrayList<>();
        List<Element> items = element.elements("branch");
        for (Element item : items) {
            Branch branch = new Branch();
            String code, name;
            code = item.attributeValue("code");
            name = item.attributeValue("name");
            List<Link> links = parseLinks(item.element("links"), branch);
            branch.setCode(code);
            branch.setName(name);
            branch.setLinks(links);
            branch.setExecute(parseExecutes(item.element("executes"), new HashMap<>(), code));
            branches.add(branch);
        }
        return branches;
    }

    private List<Execute> parseExecutes(Element element, Map<String, Action> actionMap, String msg) {
        List<Execute> executers = new ArrayList<>();
        if (element == null) {
            return executers;
        }
        List<Element> items = element.elements("execute");
        for (Element item : items) {
            Execute execute = ruleConfig.getExecuteParser().doParse(item, actionMap);
            if (execute != null) {
                executers.add(execute);
            }
        }
        if (executers.size() != executers.stream().map(o -> o.getCode()).distinct().collect(Collectors.toList()).size()) {
            throw new RuleException(msg + ":步骤编码不能重复");
        }
        return executers;
    }


    /**
     * 处理链接节点
     *
     * @param element
     * @param branch
     * @return
     */
    private List<Link> parseLinks(Element element, Branch branch) {
        List<Link> links = new ArrayList<>();
        List<Element> items = element.elements("link");
        for (Element item : items) {
            Link link = new Link();
            String code, name, priority, nextType, nextResultCode, nextBranchCode, eternal;
            eternal = item.attributeValue("eternal");
            code = item.attributeValue("code");
            name = item.attributeValue("name");
            priority = item.attributeValue("priority");
            nextType = item.attributeValue("nextType");
            nextResultCode = item.attributeValue("nextResultCode");
            nextBranchCode = item.attributeValue("nextBranchCode");

            link.setCode(code);
            link.setName(name);
            link.setBranchCode(branch.getCode());
            link.setPriority(Integer.parseInt(priority));
            link.setNextType(nextType);
            link.setNextBranchCode(nextBranchCode);
            link.setNextResultCode(nextResultCode);
            if ("true".equalsIgnoreCase(eternal)) {
                link.setEternal(true);
            } else {
                link.setEternal(false);
                link.setExpression(parseExpression(item.element("expression")));
            }

            links.add(link);
        }
        return links;
    }

    /**
     * 处理条件节点
     *
     * @param element
     * @return
     */
    public static Expression parseExpression(Element element) {

        if (element == null) {
            return null;
        }
        Expression expression = new Expression();
        String name, coverComplex, expressionType, cover, operationCode, dataType, thresholdComplex, thresholdType, threshold;

        name = element.attributeValue("name");
        expressionType = element.attributeValue("expressionType");
        operationCode = element.attributeValue("operationCode");
        dataType = element.attributeValue("dataType");
        if (Constant.ExpressionType.RELATION.equalsIgnoreCase(expressionType)) {
            expressionType = Constant.ExpressionType.RELATION;

            coverComplex = element.attributeValue("coverComplex");
            cover = element.attributeValue("cover");
            thresholdComplex = element.attributeValue("thresholdComplex");
            threshold = element.attributeValue("threshold");


            if (StringUtils.isNotEmpty(coverComplex)) {
                expression.setCoverComplex(Integer.parseInt(coverComplex));
            } else {
                expression.setCoverComplex(0);
            }
            if (StringUtils.isNotEmpty(cover)) {
                expression.setCover(cover);
            }

            if (StringUtils.isNotEmpty(thresholdComplex)) {
                expression.setThresholdComplex(Integer.parseInt(thresholdComplex));
            } else {
                expression.setThresholdComplex(0);
            }
            if (StringUtils.isNotEmpty(threshold)) {
                expression.setThreshold(threshold);
            }

        } else if (Constant.ExpressionType.LOGIC.equalsIgnoreCase(expressionType)) {
            expressionType = Constant.ExpressionType.LOGIC;
            List<Expression> subConditions = new ArrayList<>();
            List<Element> items = element.element("subExpressions").elements("expression");
            for (Element item : items) {
                subConditions.add(parseExpression(item));
            }
            expression.setSubExpression(subConditions);
        }
        expression.setName(name);
        expression.setExpressionType(expressionType);
        expression.setOperationCode(operationCode);
        expression.setDataType(dataType);
        return expression;
    }

    /**
     * 处理结果节点
     *
     * @param element
     * @return
     */
    private List<Result> parseResults(Element element) {
        List<Result> results = new ArrayList<>();
        List<Element> items = element.elements("result");
        for (Element item : items) {
            Result result = new Result();
            String code, name, resultType, dataType, data;
            code = item.attributeValue("code");
            name = item.attributeValue("name");
            dataType = item.attributeValue("dataType");
            data = item.attributeValue("data");
            result.setCode(code);
            result.setName(name);
            result.setDataType(dataType);
            result.setData(data);
            results.add(result);
        }
        return results;
    }
}
