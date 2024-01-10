package org.dromara.testhub.nsrule.parserXml;//package org.dromara.testhub.nsrule.parserXml;
//
//import org.dromara.testhub.nsrule.context.executer.core.RuleConfig;
//import org.dromara.testhub.nsrule.core.executer.mode.base.bound.*;
//import org.dromara.testhub.nsrule.ruleLine.mode.executer.core.Expression;
//import org.dromara.testhub.nsrule.core.parser.BoundParser2;
//import org.apache.commons.lang3.StringUtils;
//import org.dom4j.Element;
//import org.dom4j.QName;
//import org.dom4j.tree.AbstractNode;
//import org.dom4j.tree.DefaultElement;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author: 失败女神-vinc
// * @email: 18733123202@163.com
// * @date: 2022/9/16 18:12
// */
//public class XmlBoundParser implements BoundParser2<Element> {
//    public Compose boundCompose(Element element) {
//        if (element == null) {
//            return null;
//        }
//
//        List content = element.content();
//
//        Compose compose = new Compose();
//
//        for (Object node : content) {
//            int type = ((AbstractNode) node).getNodeType();
//            if (type == 3 || type == 4) {
//                //Text //CDATA
//                String text = ((AbstractNode) node).getText().trim();
//                if (StringUtils.isEmpty(text)) {
//                    continue;
//                }
////                text = "\n"+text;
//                Str bound = new Str();
//                bound.setText(text, RuleConfig.getInstance());
//                if (bound != null) {
//                    compose.addItem(bound);
//                }
//
//            } else if (type == 1) {
//                //Element
//                Bound bound = boundElement((DefaultElement) node);
//                if (bound != null) {
//                    compose.addItem(bound);
//                }
//            }
//
//        }
//
//
//        return compose;
//    }
//
//    public Bound boundElement(Element element) {
//        if (element == null) {
//            return null;
//        }
//        QName qName = element.getQName();
//        String type = qName.getName();
//        if ("for".equalsIgnoreCase(type)) {
//            return boundFor(element);
//        } else if ("choice".equalsIgnoreCase(type)) {
//            return boundChoice(element);
//        } else if ("where".equalsIgnoreCase(type)) {
//            return boundWhere(element);
//        } else if ("if".equalsIgnoreCase(type)) {
//            return boundIf(element);
//        } else if ("else".equalsIgnoreCase(type)) {
//            return boundElse(element);
//        } else if ("trim".equalsIgnoreCase(type)) {
//            return boundTrim(element);
//        }
//        return null;
//    }
//
//    public Trim boundTrim(Element element) {
//        if (element == null) {
//            return null;
//        }
//        Trim bound = new Trim();
//        bound.setCompose(boundCompose(element));
//        return bound;
//    }
//
//    public Else boundElse(Element element) {
//        if (element == null) {
//            return null;
//        }
//        Else bound = new Else();
//        bound.setCompose(boundCompose(element));
//        return bound;
//    }
//
//    public If boundIf(Element element) {
//        if (element == null) {
//            return null;
//        }
//        If bound = new If();
//        bound.setCompose(boundCompose(element));
//        Expression expression = XMLRuleParser.parseExpression(element.element("expression"));
//        bound.setExpression(expression);
//        return bound;
//    }
//
//    public Choice boundChoice(Element element) {
//        if (element == null) {
//            return null;
//        }
//        Choice bound = new Choice();
//        List<If> ifs = new ArrayList<>();
//        List<Element> elements = element.elements("if");
//        for (Element item : elements) {
//            ifs.add(boundIf(item));
//        }
//        bound.setIfs(ifs);
//        bound.setElseNode(boundElse(element.element("else")));
//        return bound;
//    }
//
//    public Where boundWhere(Element element) {
//        if (element == null) {
//            return null;
//        }
//        Where bound = new Where();
//        List<If> ifs = new ArrayList<>();
//        List<Element> elements = element.elements("if");
//        for (Element item : elements) {
//            ifs.add(boundIf(item));
//        }
//        bound.setIfs(ifs);
//        bound.setElseNode(boundElse(element.element("else")));
//        return bound;
//    }
//
//    public Str boundStr(Element element) {
//        if (element == null) {
//            return null;
//        }
//        return new Str();
//    }
//
//    public Formula boundFormula(Element element) {
//        if (element == null) {
//            return null;
//        }
//        Formula formula = new Formula();
//        formula.setData(element.attributeValue("data"));
//        return formula;
//    }
//
//    public For boundFor(Element element) {
//        if (element == null) {
//            return null;
//        }
//        String dataType, data, itemName, index, start, end;
//        dataType = element.attributeValue("dataType");
//        data = element.attributeValue("data");
//        itemName = element.attributeValue("itemName");
//        start = element.attributeValue("start");
//        index = element.attributeValue("index");
//        end = element.attributeValue("end");
//        For bound = new For();
//        bound.setDataType(dataType);
//        bound.setData(data);
//        bound.setItemName(itemName);
//        bound.setIndexName(index);
//        bound.setStart(StringUtils.isNotEmpty(start) ? Integer.valueOf(start) : -1);
//        bound.setEnd(StringUtils.isNotEmpty(end) ? Integer.valueOf(end) : -1);
//        bound.setCompose(boundCompose(element));
//        return bound;
//    }
//}
