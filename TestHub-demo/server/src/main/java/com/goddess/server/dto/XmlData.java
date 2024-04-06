package com.goddess.server.dto;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "xmlData")
public class XmlData {

    @XmlElement
    private String field1;

    @XmlElement
    private int field2;

    // Getter and Setter methods for field1 and field2

    public String toXmlString() {
        // 将对象转换为 XML 字符串的逻辑
        // 这里只是一个示例，你可以根据实际需求自行编写逻辑
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<xmlData>");
        xmlBuilder.append("<field1>").append(field1).append("</field1>");
        xmlBuilder.append("<field2>").append(field2).append("</field2>");
        xmlBuilder.append("</xmlData>");
        return xmlBuilder.toString();
    }
}
