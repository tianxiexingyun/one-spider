package org.chobit.spider.sites.wenku8;

import org.chobit.spider.bean.PostContent;
import org.chobit.spider.process.transform.AbstractTransformer;
import org.jsoup.nodes.Document;

import static org.chobit.common.constants.Symbol.SPACE;

/**
 * @author robin
 */
public class Wenku8Transformer extends AbstractTransformer {


    @Override
    public PostContent extract(Document docPost, String bakTitle, String parent) {
        PostContent content = super.extract(docPost, bakTitle, parent);
        String title = content.getTitle();
        String[] arr = title.split(SPACE);
        if (arr.length == 4) {
            content.setVolumeName(arr[0] + SPACE + arr[1]);
            content.setChapterName(arr[2] + SPACE + arr[3]);
        }
        if (arr.length == 3) {
            if (title.startsWith("短篇")) {
                content.setVolumeName(arr[0]);
                content.setChapterName(arr[1] + SPACE + arr[2]);
            } else {
                content.setVolumeName(arr[0] + SPACE + arr[1]);
                content.setChapterName(arr[2]);
            }
        }
        if (arr.length == 2) {
            content.setVolumeName(arr[0]);
            content.setChapterName(arr[1]);
        }

        return content;
    }

    @Override
    public String postUrlSelector() {
        return "body > table > tbody > tr > td > a";
    }

    @Override
    public String postTitleSelector() {
        return "#title";
    }

    @Override
    public String postContentSelector() {
        return "#content";
    }
}
