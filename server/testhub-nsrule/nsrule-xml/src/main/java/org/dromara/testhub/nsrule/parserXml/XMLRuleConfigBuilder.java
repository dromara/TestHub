package org.dromara.testhub.nsrule.parserXml;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.resource.Resource;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.net.URLDecoder;
import org.dromara.testhub.nsrule.core.constant.Constant;
import org.dromara.testhub.nsrule.core.constant.ExceptionCode;
import org.dromara.testhub.nsrule.core.constant.RuleException;
import org.dromara.testhub.nsrule.core.executer.context.RuleProject;
import org.dromara.testhub.nsrule.core.executer.context.RuleConfig;
import org.dromara.testhub.nsrule.core.executer.meta.MetaClass;
import org.dromara.testhub.nsrule.core.executer.meta.MetaEnum;
import org.dromara.testhub.nsrule.core.executer.meta.MetaProperty;
import org.dromara.testhub.nsrule.core.executer.mode.base.action.Action;
import org.dromara.testhub.nsrule.core.executer.mode.base.action.Param;
import org.dromara.testhub.nsrule.core.executer.mode.base.function.FunctionHandlerFactory;
import org.dromara.testhub.nsrule.core.executer.mode.Rule;
import org.dromara.testhub.nsrule.core.executer.operation.OperationFactory;
import org.dromara.testhub.nsrule.core.expand.FormulaBuilder;
import org.dromara.testhub.nsrule.core.parser.ActionParser;
import org.dromara.testhub.nsrule.core.parser.ExecuteParser;
import org.dromara.testhub.nsrule.core.parser.RuleConfigBuilder;
import org.dromara.testhub.nsrule.core.parser.RuleParser;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.File;
import java.nio.charset.Charset;
import java.util.*;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/4 15:51
 */
public class XMLRuleConfigBuilder implements RuleConfigBuilder<String> {

    public RuleConfig build(String configPath) throws Exception {
        Document document = DocumentHelper.parseText(ResourceUtil.readUtf8Str(configPath));
        if (!document.getRootElement().getName().equals("configuration")) {
            throw new RuleException(ExceptionCode.EC_0002);
        }

        //初始化
        RuleConfig ruleConfig = RuleConfig.getInstance();

        String rulePath = document.getRootElement().element("rulePath").getTextTrim();
        ruleConfig.setRulePath(rulePath);

        //操作符工厂
        ruleConfig.setRelationFactory(OperationFactory.getInstance());

        //解析 公式解析器
        FormulaBuilder formulaBuilder = parseFormulaBuilder(document.getRootElement().element("formulaBuilder"));
        if (formulaBuilder != null) {
            ruleConfig.setFormulaBuilder(formulaBuilder);
        }

        //解析扩展方法
        ruleConfig.setFunctionHandlerFactory(FunctionHandlerFactory.getInstance());

        //解析自定义 actionParser解析器
        ActionParser actionParser = parseActionParser(document.getRootElement().element("actionParser"));
        ruleConfig.setActionParser(new XmlActionDefaultParser(actionParser));

        //解析自定义 executeParser解析器
        ExecuteParser executeParser = parseExecuteParser(document.getRootElement().element("executeParser"));
        ruleConfig.setExecuteParser(executeParser);

        // 解析系统级行为
        List<Action> actions = parseActions(document.getRootElement().element("actions"), ruleConfig,"系统级");
        ruleConfig.setActions(actions);

        List<RuleProject> projects = parseProjects(document.getRootElement().element("projects"),ruleConfig,actions);

        ruleConfig.setProjectMap(projects);

        //解析规则配置
        List<Rule> rules = parseRules(ruleConfig.getRulePath(), ruleConfig);
        for(Rule rule:rules){
            ruleConfig.addRule(rule);
            RuleProject ruleProject = ruleConfig.getProjectMap().get(rule.getProject());
            ruleProject.addRule(rule);
        }

        return ruleConfig;
    }
    public static List<RuleProject> parseProjects(Element element, RuleConfig ruleConfig,List<Action> globalActions) throws Exception {
        if (element == null) {
            return new ArrayList<>();
        }
        List<RuleProject> projects = new ArrayList<>();
        List<Element> items = element.elements("project");
        if(CollectionUtil.isEmpty(items)){
            RuleProject project = new RuleProject(ruleConfig);
            project.setCode("default");
            project.setName("默认项目");
            projects.add(project);
            return projects;
        }
        for (Element item : items) {
            RuleProject project = new RuleProject(ruleConfig);
            String  code, name;

            code = item.attributeValue("code");
            name = item.attributeValue("name");
            project.setCode(code);
            project.setName(name);

            //解析枚举配置
            List<MetaEnum> metaEnums = parseMetaEnums(item.element("metaEnums"));
            project.setMetaEnums(metaEnums);

            //解析元数据配置
            List<MetaClass> metaClasses = parseMetaClasses(item.element("metaClasses"), new ArrayList<>());
            project.setMetaClasses(metaClasses);

            //解析公共参数
            parseGlobalParams(item,project);

            // 解析项目级行为
            List<Action> actions = parseActions(item.element("actions"), project, project.getMetaClassMap(), "项目:"+name);
            actions.addAll(globalActions);
            project.setActions(actions);

            projects.add(project);
        }

        return projects;

    }
    public static void parseGlobalParams(Element element,RuleProject project) {
        //解析公共参数
        List<Element> items = element.elements("globalParams");
        for (Element item : items) {
            List<Param> globalParams = parseParams(item);
            String env = item.attributeValue("env");
            if(StringUtils.isEmpty(env)&&items.size()==1){
                project.putGlobalParams("default",globalParams);
                return;
            }

            if (StringUtils.isEmpty(env)){
                throw new RuleException("项目"+project.getCode()+"的全局参数必须配置env环境");
            }
            project.putGlobalParams(env,globalParams);
        }
    }

    /**
     * 处理参数节点
     *
     * @param element
     * @return
     */
    public static List<Param> parseParams(Element element) {
        if (element == null) {
            return new ArrayList<>();
        }
        List<Param> params = new ArrayList<>();
        List<Element> items = element.elements("param");
        for (Element item : items) {
            Param param = new Param();
            String code, name, dataType, necessary, data;
            code = item.attributeValue("code");
            name = item.attributeValue("name");
            dataType = item.attributeValue("dataType");
            necessary = item.attributeValue("necessary");
            data = item.attributeValue("data");
            param.setCode(code);
            param.setName(name);
            param.setDataType(dataType);
            if (StringUtils.isNotEmpty(necessary) && necessary.equalsIgnoreCase("true")) {
                param.setNecessary(true);
            } else {
                param.setNecessary(false);
                if (StringUtils.isNotEmpty(data)) {
                    param.setData(data);
                }
            }
            params.add(param);
        }
        return params;
    }

    public static List<MetaClass> parseMetaClasses(Element element, List<MetaEnum> metaEnums) {
        List<MetaClass> metaClasses = new ArrayList<>();
        if (element == null) {
            return metaClasses;
        }
        List<Element> items = element.elements("metaClass");
        for (Element item : items) {
            String code, name, loaderCode;
            code = item.attributeValue("code");
            name = item.attributeValue("name");
            loaderCode = item.attributeValue("loaderCode");

            MetaClass metaClass = new MetaClass();
            metaClass.setCode(code);
            metaClass.setName(name);
            metaClass.setLoaderCode(loaderCode);

            List<MetaProperty> properties = new ArrayList<>();
            List<Element> items2 = item.element("properties").elements("property");
            for (Element item2 : items2) {
                String code2, name2, dataType, primary, enumCode, metaClassCode;
                code2 = item2.attributeValue("code");
                name2 = item2.attributeValue("name");
                dataType = item2.attributeValue("dataType");
                primary = item2.attributeValue("primary");
                enumCode = item2.attributeValue("enumCode");
                metaClassCode = item2.attributeValue("metaClassCode");

                MetaProperty property = new MetaProperty();
                property.setCode(code2);
                property.setName(name2);
                property.setDataType(dataType);
                if (StringUtils.isNotEmpty(primary) && primary.equalsIgnoreCase("true")) {
                    property.setPrimary(true);
                } else {
                    property.setPrimary(false);
                }
                property.setDataType(dataType);
                property.setEnumCode(enumCode);
                property.setMetaClassCode(metaClassCode);

                properties.add(property);
            }
            metaClass.setProperties(properties);
            metaClasses.add(metaClass);
        }
        return metaClasses;
    }

    public static List<MetaEnum> parseMetaEnums(Element element) {
        List<MetaEnum> metaEnums = new ArrayList<>();
        if (element == null) {
            return metaEnums;
        }
        List<Element> items = element.elements("metaEnum");
        for (Element item : items) {
            String code, name, type;
            code = item.attributeValue("code");
            name = item.attributeValue("name");
            type = item.attributeValue("type");

            MetaEnum metaEnum = new MetaEnum();
            metaEnum.setCode(code);
            metaEnum.setName(name);
            metaEnum.setType(type);
            if (Constant.EnumType.FIXED.equalsIgnoreCase(type)) {
                List<MetaEnum.Item> itemList = new ArrayList<>();
                //固定的要解析明细项
                List<Element> items2 = item.element("items").elements("item");
                for (Element item2 : items2) {
                    String code2, name2, pCode2;
                    code2 = item2.attributeValue("code");
                    name2 = item2.attributeValue("name");
                    pCode2 = item2.attributeValue("pCode");
                    MetaEnum.Item temp = new MetaEnum.Item();
                    temp.setCode(code2);
                    temp.setName(name2);
                    temp.setpCode(pCode2);
                    itemList.add(temp);
                }
                metaEnum.setItems(itemList);
            } else {
                //动态的要解析 加载路径
                String classPath = item.attributeValue("classPath");
                metaEnum.setClassPath(classPath);
            }
            metaEnums.add(metaEnum);
        }
        return metaEnums;
    }

    /**
     * 处理action
     *
     * @return
     */
    public static List<Action> parseActions(Element element, RuleProject project, Map<String, MetaClass> metaClassMap, String msg) {
        List<Action> actions = new ArrayList<>();
        if (element == null) {
            return actions;
        }
        List<Element> items = element.elements("action");
        for (Element item : items) {
            Action action = project.getRuleConfig().getActionParser().parse(item, null);
            if (action != null) {
                actions.add(action);
            }
            if (!project.getRuleConfig().isInnerDataType(action.getDataType())) {
                //非基础数据类型
                if (project.getMetaClassByDataType(action.getDataType()) == null) {
                    //还不是全局的
                    if (!metaClassMap.containsKey(action.getDataType())) {
                        //看看规则中也没有
                        throw new RuleException(ExceptionCode.EC_0109, msg, action.getCode(), action.getDataType());
                    }
                }
            }
        }
        return actions;
    }
    public static List<Action> parseActions(Element element, RuleConfig ruleConfig,String msg) {
        List<Action> actions = new ArrayList<>();
        if (element == null) {
            return actions;
        }
        List<Element> items = element.elements("action");
        for (Element item : items) {
            Action action = ruleConfig.getActionParser().parse(item, null);
            if (action != null) {
                actions.add(action);
            }
            if (!ruleConfig.isInnerDataType(action.getDataType())) {
                //非基础数据类型
                throw new RuleException(ExceptionCode.EC_0109, msg, action.getCode(), action.getDataType());
            }
        }
        return actions;
    }

    private FormulaBuilder parseFormulaBuilder(Element element) {
        if (element == null) {
            return null;
        }
        String classPath = element.attributeValue("classPath");
        try {
            Class<? extends FormulaBuilder> clszz = (Class<? extends FormulaBuilder>) Class.forName(classPath);
            FormulaBuilder formulaBuilder = clszz.newInstance();
            return formulaBuilder;
        } catch (Exception e) {
            throw new RuleException(ExceptionCode.EC_0006, classPath);
        }
    }

    private ActionParser parseActionParser(Element element) {
        if (element == null) {
            return null;
        }
        String classPath = element.attributeValue("classPath");
        try {
            Class<? extends ActionParser> clszz = (Class<? extends ActionParser>) Class.forName(classPath);
            ActionParser actionParser = clszz.newInstance();
            return actionParser;
        } catch (Exception e) {
            throw new RuleException(ExceptionCode.EC_0008, classPath);
        }
    }

    private ExecuteParser parseExecuteParser(Element element) {
        if (element == null) {
            return new XmlExecuteDefaultParser(null);
        }
        String classPath = element.attributeValue("classPath");
        try {
            Class<? extends ExecuteParser> clazz = (Class<? extends ExecuteParser>) Class.forName(classPath);
            ExecuteParser parser = clazz.newInstance();
            ;
            return new XmlExecuteDefaultParser(parser);
        } catch (Exception e) {
            throw new RuleException(ExceptionCode.EC_0009, classPath);
        }
    }

    private static List<Rule> parseRules(String rulePath, RuleConfig ruleConfig) throws Exception {
        List<Rule> rules = new ArrayList<>();
        //解析规则
        List<String> rulePaths = Arrays.asList(rulePath.split(","));

        for (String path : rulePaths) {
            Resource resource = ResourceUtil.getResourceObj("classpath:" + path);
            parsePath(URLDecoder.decode(resource.getUrl().getPath(), Charset.defaultCharset()), ruleConfig, rules);
        }
        return rules;
    }

    private static void parsePath(String path, RuleConfig ruleConfig, List<Rule> rules) throws Exception {
        File file = new File(path);
        if (!file.exists()) {
            throw new RuleException("不存在的路径" + path);
        }
        if (file.isDirectory()) {
            File[] tempList = file.listFiles();
            for (File f : tempList) {
                if (!f.isHidden()) {
                    if (f.isDirectory()) {
                        File[] subList = f.listFiles();
                        for (File sf : subList) {
                            parsePath(sf.getAbsolutePath(), ruleConfig, rules);
                        }
                    } else {
                        try {
                            Rule rule = parseRule(f.getPath(),ruleConfig);
                            rules.add(rule);
                        } catch (Exception e) {
                            System.out.println("解析失败:" + e.getMessage() + ",文件:" + path);
                            e.printStackTrace();
                        }
                    }
                }
            }
        } else {
            try {
                Rule rule = parseRule(path,ruleConfig);
                rules.add(rule);
            } catch (Exception e) {
                System.out.println("解析失败:" + e.getMessage() + ",文件:" + path);
                e.printStackTrace();
            }
        }
    }

    private static Rule parseRule(String path,RuleConfig ruleConfig) throws Exception {
        Rule rule = null;
        RuleParser parser = null;
        String type = path.substring(path.lastIndexOf(".") + 1).toLowerCase();
        switch (type) {
            case Constant.ConfigType.XML:
                parser = XMLRuleParser.getInstance(ruleConfig);
                rule = parser.parse(DocumentHelper.parseText(ResourceUtil.readUtf8Str(path)), ruleConfig);
                break;
            default:
                throw new RuleException(ExceptionCode.EC_0001, path);
        }
        rule.setSource(path);
        return rule;
    }
}
