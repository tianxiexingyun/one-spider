package org.chobit.spider.bean;

/**
 * @author robin
 */
public class Anchor {


    /**
     * 链接标题
     */
    private String name;

    /**
     * 链接路径
     */
    private String url;

    /**
     * 上级标题
     */
    private String parent;


    public Anchor(String name) {
        this.name = name;
    }

    public Anchor(String name, String href) {
        this.name = name;
        this.url = href;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (null != parent) {
            builder.append(parent).append(" - ");
        }
        builder.append(name).append(" : ").append(url);
        return builder.toString();
    }
}
