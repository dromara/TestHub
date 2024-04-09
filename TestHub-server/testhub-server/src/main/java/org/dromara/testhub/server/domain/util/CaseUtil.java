package org.dromara.testhub.server.domain.util;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.QName;
import org.dom4j.tree.AbstractNode;
import org.dom4j.tree.DefaultText;
import org.dromara.testhub.framework.exception.TestHubException;
import org.dromara.testhub.server.domain.dto.req.rule.RuleDocumentReqDto;

import java.util.Arrays;
import java.util.List;

public class CaseUtil {
    private static final List<String> SKIPS = Arrays.asList("script", "bound");

    public static Document getDocument(RuleDocumentReqDto documentReqDto) {
        Document document = getDocument(documentReqDto.getDocumentStr());
        document.getRootElement().addAttribute("code", documentReqDto.getCode());
        document.getRootElement().addAttribute("project", documentReqDto.getProjectCode());
        return document;
    }

    //根据xml 字符串获取Dom对象
    public static Document getDocument(String documentStr) {
        Document document = null;
        try {
            document = DocumentHelper.parseText(documentStr);
            List<Element> items = document.content();
            for (Element item : items) {
                remove(item);
            }
        } catch (Exception e) {
            throw new TestHubException(e.getMessage());
        }
        return document;
    }

    //移除Dom对象中多余的空格
    public static void remove(Element root) {
        if (root == null) {
            return;
        }
        List items = root.content();

        for (int i = items.size() - 1; i >= 0; i--) {
            Object item = items.get(i);
            AbstractNode node = ((AbstractNode) item);
            int type = node.getNodeType();
            if (type == 3) {
                String text = (node.getText()).replaceAll(" ", "").replaceAll("\n", "");
                if (text.length() < 1) {
                    QName qName = node.getParent().getQName();
                    String name = qName.getName();
                    if (!SKIPS.contains(name.toLowerCase())) {
                        root.remove((DefaultText) item);
                    }

                }
            } else if (type != 8 && type != 4) {
                remove((Element) item);
            }
        }
    }
}
