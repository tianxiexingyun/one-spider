package org.chobit.spider.sites.epub;

import org.chobit.spider.bean.Anchor;
import org.chobit.spider.process.transform.AbstractTransformer;
import org.jsoup.nodes.Document;

import java.util.LinkedList;
import java.util.List;

import static org.chobit.spider.tools.UrlHelper.buildUrl;

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
        List<Anchor> anchors = new LinkedList<>();
        for (int i = 1; i <= total; i++) {
            String href = "OPS/chapter" + i + ".html";
            String url = buildUrl(href, baseUrl);
            Anchor anchor = new Anchor(String.valueOf(i), url);
            anchors.add(anchor);
        }
        return anchors;
    }
}
