package org.dromara.testhub.common.util;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.*;

public class FileUtil {
    /**
     * 判断文件是否存在，不存在就创建
     *
     * @param file
     */
    public static void createFile(File file) {
        if (!file.exists()) {
            //getParentFile() 获取上级目录（包含文件名时无法直接创建目录的）
            if (!file.getParentFile().exists()) {
                //创建上级目录
                file.getParentFile().mkdirs();
            }
            try {
                //在上级目录里创建文件
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static String formatXml(Document document)  {
        try {
            OutputFormat format = OutputFormat.createPrettyPrint(); // 创建格式化输出格式
            format.setIndentSize(4);  // 行缩进
            format.setNewlines(true); // 一个结点为一行
            format.setPadText(true);
            StringWriter writer = new StringWriter();
            XMLWriter xmlWriter = new CustomXMLWriter(writer, format); // 创建XMLWriter对象
            xmlWriter.write(document); // 将Document对象写入字符串
            xmlWriter.flush();
            xmlWriter.close();
            return writer.toString(); // 返回格式化的字符串
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document.asXML();
    }

    public static void toFileXml(File file, Document document) {
        OutputFormat format = new OutputFormat();
        format.setIndentSize(4);  // 行缩进
        format.setNewlines(true); // 一个结点为一行
        format.setPadText(true);
        format.setNewLineAfterDeclaration(false); // 放置xml文件中第二行为空白行

        XMLWriter writer = null;
        try {
            FileUtil.createFile(file);
            writer = new CustomXMLWriter(new FileOutputStream(file), format);
            writer.write(document);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class CustomXMLWriter extends XMLWriter {
        private boolean inBoundTag = false;

        public CustomXMLWriter(Writer writer,OutputFormat format) {
            super(writer,format);
        }
        public CustomXMLWriter(OutputStream out, OutputFormat format) throws UnsupportedEncodingException {
            super(out,format);
        }

        @Override
        protected void writeNode(Node node) throws IOException {
            if (node instanceof Element) {
                Element element = (Element) node;
                if ("bound".equals(element.getName())) {
                    inBoundTag = true;
                }
            }

            super.writeNode(node);

            if (node instanceof Element) {
                Element element = (Element) node;
                if ("bound".equals(element.getName())) {
                    inBoundTag = false;
                }
            }
        }

        @Override
        protected void writeString(String text) throws IOException {
            if (inBoundTag) {
                // 忽略 <bound> 标签内部内容的格式化
                writer.write(text);
            } else {
                super.writeString(text);
            }
        }
    }
}
