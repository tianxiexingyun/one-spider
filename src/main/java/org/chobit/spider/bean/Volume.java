package org.chobit.spider.bean;

import java.util.LinkedList;
import java.util.List;

/**
 * @author robin
 */
public class Volume {

    /**
     * 卷名
     */
    private String name;

    /**
     * 章节列表
     */
    private final List<PostContent> chapters = new LinkedList<>();


    public Volume() {
    }

    public Volume(String name) {
        this.name = name;
    }

    public void addChapter(PostContent chapter) {
        this.chapters.add(chapter);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PostContent> getChapters() {
        return chapters;
    }

}
