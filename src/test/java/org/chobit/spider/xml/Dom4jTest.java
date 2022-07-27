package org.chobit.spider.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.util.List;

/**
 * @author robin
 */
public class Dom4jTest {


    @Test
    public void read() throws DocumentException {
        String url = "http://localhost:82/toc.ncx";
        SAXReader reader = new SAXReader();
        Document document = reader.read(url);

        System.out.println("Root element :" + document.getRootElement().getName());

        List<Node> nodes = document.selectNodes("//*[local-name()='navPoint']");
        for (Node node : nodes) {
            System.out.println("Current Element :" + node.selectSingleNode("//*[local-name()='text']").getText());
            System.out.println("Current Element :" + node.selectSingleNode("//*[local-name()='content']").valueOf("@src"));
        }

    }


}
