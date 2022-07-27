package org.chobit.spider.sites.epub;

import org.chobit.spider.bean.Anchor;
import org.chobit.spider.process.transform.AbstractTransformer;
import org.dom4j.Node;
import org.jsoup.nodes.Document;

import java.util.LinkedList;
import java.util.List;

import static org.chobit.spider.tools.UrlHelper.buildUrl;
import static org.chobit.spider.tools.XmlHelper.readXml;

/**
 * @author robin
 */
public abstract class AbstractEpubTransformer extends AbstractTransformer {


    private final int total;

    public AbstractEpubTransformer(int total) {
        this.total = total;
    }


    @Override
    public List<Anchor> postAnchors(Document doc, String baseUrl) {
        String urlToc = "/toc.ncx";
        urlToc = buildUrl(urlToc, baseUrl);

        List<Anchor> anchors = new LinkedList<>();
        org.dom4j.Document docToc = readXml(urlToc);
        if (null == docToc) {
            logger.error("read toc.ncx failed.");
            return anchors;
        }

        List<Node> nodes = docToc.selectNodes("//navPoint");
        for (Node node : nodes) {
            String href = node.selectSingleNode("content").valueOf("@src");
            String title = node.selectSingleNode("navLabel/text").getText();
            String url = buildUrl(href, baseUrl);
            anchors.add(new Anchor(title, url));
        }
        return anchors;
    }
}
