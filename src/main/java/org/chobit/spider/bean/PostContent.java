package org.chobit.spider.bean;

import java.util.LinkedList;
import java.util.List;

import static org.chobit.common.constants.Symbol.EMPTY;

/**
 * @author robin
 */
public class PostContent {

    /**
     * 标题名
     */
    private String title;

    /**
     * 卷名
     */
    private String volumeName;

    /**
     * 章节名
     */
    private String chapterName;

    /**
     * 内容
     */
    private final List<String> lines = new LinkedList<>();

    /**
     * 内容原始网页
     */
    private String contentHtml;


    public PostContent() {
    }

    public PostContent(String title) {
        this.title = title;
    }


    public void addLine(String line) {
        this.lines.add(line);
    }


    public String firstLine() {
        if (lines.isEmpty()) {
            return EMPTY;
        }
        return lines.get(0);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVolumeName() {
        return volumeName;
    }

    public void setVolumeName(String volumeName) {
        this.volumeName = volumeName;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public List<String> getLines() {
        return lines;
    }

    public String getContentHtml() {
        return contentHtml;
    }

    public void setContentHtml(String contentHtml) {
        this.contentHtml = contentHtml;
    }
}
