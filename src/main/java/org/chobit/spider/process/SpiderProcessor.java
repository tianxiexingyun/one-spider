package org.chobit.spider.process;

import org.chobit.common.http.HttpClient;
import org.chobit.spider.bean.Anchor;
import org.chobit.spider.bean.PostContent;
import org.chobit.spider.process.sink.Sink;
import org.chobit.spider.process.src.Source;
import org.chobit.spider.process.transform.Transformer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

/**
 * @author robin
 */
public class SpiderProcessor {


    private final Source source;

    private final Transformer transformer;

    private final Sink sink;


    public SpiderProcessor(Source source, Transformer transformer, Sink sink) {
        this.source = source;
        this.transformer = transformer;
        this.sink = sink;
    }


    public void process() {
        Charset charset = source.charset();

        Document docIndex = getDoc(source.indexUrl(), charset);
        List<Anchor> anchors = transformer.postAnchors(docIndex, source.baseUrl());

        List<PostContent> contents = new LinkedList<>();
        for (Anchor anchor : anchors) {
            Document docPost = getDoc(anchor.getUrl(), charset);
            PostContent content = transformer.extract(docPost, anchor.getName(), anchor.getParent());
            // 检查各行是否需要删除
            content.getLines().removeIf(this::toDel);
            // 内容为空时不做写入
            if (content.getLines().isEmpty()) {
                continue;
            }
            contents.add(content);
        }

        sink.sink(contents);
    }


    private boolean toDel(String line) {
        for (String s : source.blacklist()) {
            if (line.contains(s)) {
                return true;
            }
        }
        return false;
    }

    private Document getDoc(String url, Charset charset) {
        byte[] bytes = HttpClient.getForBytes(url);
        return Jsoup.parse(new String(bytes, charset));
    }
}
