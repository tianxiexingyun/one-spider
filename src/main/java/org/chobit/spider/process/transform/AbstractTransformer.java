package org.chobit.spider.process.transform;

import org.chobit.common.utils.Collections2;
import org.chobit.spider.bean.Anchor;
import org.chobit.spider.bean.PostContent;
import org.chobit.spider.except.InvalidUrlException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.LinkedList;
import java.util.List;

import static org.chobit.common.constants.Symbol.SLASH;
import static org.chobit.common.utils.StrKit.isBlank;
import static org.chobit.spider.constants.Constants.*;

/**
 * @author robin
 */
public abstract class AbstractTransformer implements Transformer {


    @Override
    public List<Anchor> postAnchors(Document doc, String baseUrl) {
        Elements elements = doc.select(postUrlSelector());

        List<Anchor> result = new LinkedList<>();
        if (Collections2.isEmpty(elements)) {
            return result;
        }

        for (Element ele : elements) {
            String name = ele.text();
            String href = ele.attr(HREF);
            String url = buildUrl(href, baseUrl);
            result.add(new Anchor(name, url));
        }
        return result;
    }


    @Override
    public PostContent extract(Document docPost, String bakTitle, String parent) {
        Element eleTitle = docPost.selectFirst(postTitleSelector());
        Element eleContent = docPost.selectFirst(postContentSelector());
        String title = null == eleTitle ? (null == bakTitle ? "" : bakTitle) : eleTitle.text();

        PostContent content = new PostContent(title);
        content.setContentHtml(null == eleContent ? "" : eleContent.html());
        content.setChapterName(title);
        content.setVolumeName(parent);

        if (null == eleContent) {
            return content;
        }

        Elements paragraphs = eleContent.select(SELECTOR_PARAGRAPH);
        for (Element ele : paragraphs) {
            content.addLine(ele.text().trim());
        }

        if(content.getLines().isEmpty()){
            extract0(content, eleContent);
        }

        return content;
    }


    protected void extract0(PostContent content, Element ele) {
        ele.select("ul").before(REPLACER_BR).after(REPLACER_BR);
        ele.select("ol").before(REPLACER_BR).after(REPLACER_BR);
        ele.select("li").before(REPLACER_BR).after(REPLACER_BR);
        ele.select("br").before(REPLACER_BR).after(REPLACER_BR);
        ele.select("BR").before(REPLACER_BR).after(REPLACER_BR);
        String all = ele.text();
        String[] arr = all.split(REPLACER_BR);
        for (String s : arr) {
            if (isBlank(s)) {
                continue;
            }
            content.addLine(s.trim());
        }
    }


    /**
     * 拼接请求路径
     *
     * @param href 请求路径
     * @return 请求路径
     */
    private String buildUrl(String href, String baseUrl) {
        if (href.startsWith(FLAG_HTTP) || href.startsWith(FLAG_HTTPS)) {
            return href;
        }
        if (isBlank(baseUrl)) {
            throw new InvalidUrlException(href);
        }

        if (href.startsWith(SLASH) && baseUrl.endsWith(SLASH)) {
            return baseUrl + href.substring(1);
        } else if (!href.startsWith(SLASH) && !baseUrl.endsWith(SLASH)) {
            return baseUrl + SLASH + href;
        } else {
            return baseUrl + href;
        }
    }


}
