package org.chobit.spider.sites.epub;

import org.chobit.spider.bean.Anchor;
import org.chobit.spider.bean.PostContent;
import org.chobit.spider.process.transform.Transformer;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.LinkedList;
import java.util.List;

public class EpubImageTransformer implements Transformer {


    private final int total;

    private final String home;

    public EpubImageTransformer(int total, String home) {
        this.total = total;
        this.home = home;
    }

    @Override
    public List<Anchor> postAnchors(Document doc, String baseUrl) {
        List<Anchor> result = new LinkedList<>();
        for (int i = 1; i <= total; i++) {
            String url = baseUrl + i + ".html";
            Anchor anchor = new Anchor(String.valueOf(i), url);
            result.add(anchor);
        }
        return result;
    }

    @Override
    public String postUrlSelector() {
        return null;
    }

    @Override
    public String postTitleSelector() {
        return null;
    }

    @Override
    public String postContentSelector() {
        return "body > div > div > img";
    }

    @Override
    public PostContent extract(Document docPost, String bakTitle, String parent) {
        Element eleImg = docPost.selectFirst(postContentSelector());
        String imgUrl = eleImg.attr("src");

        String url = this.home + imgUrl.substring(2);

        PostContent content = new PostContent(bakTitle + ".png");
        content.addLine(url);
        return content;
    }
}
