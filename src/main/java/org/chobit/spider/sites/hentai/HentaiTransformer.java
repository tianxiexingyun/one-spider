package org.chobit.spider.sites.hentai;

import org.chobit.spider.bean.Anchor;
import org.chobit.spider.bean.PostContent;
import org.chobit.spider.process.transform.Transformer;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.LinkedList;
import java.util.List;

import static org.chobit.spider.constants.Constants.*;
import static org.chobit.spider.tools.UrlHelper.buildUrl;

/**
 * @author robin
 */
public class HentaiTransformer implements Transformer {


    @Override
    public List<Anchor> postAnchors(Document docIndex, String baseUrl) {
        Elements elements = docIndex.select(postUrlSelector());
        List<Anchor> result = new LinkedList<>();
        for (Element ele : elements) {
            String href = ele.attr(HREF);
            result.add(new Anchor(FLAG_IMG, buildUrl(href, baseUrl)));
        }
        return result;
    }

    @Override
    public PostContent extract(Document docPost, String title, String parent) {
        String name = docPost.select(postTitleSelector()).text();
        Element eleImg = docPost.selectFirst(postContentSelector());
        String imgUrl = eleImg.attr("src");

        PostContent content = new PostContent(name + ".jpg");
        content.addLine(imgUrl);
        return content;
    }


    @Override
    public String postUrlSelector() {
        return "#thumbnail-container > div > a";
    }

    @Override
    public String postTitleSelector() {
        return "#pagination-page-top > button > span.current";
    }

    @Override
    public String postContentSelector() {
        return "#image-container > a > img";
    }
}
