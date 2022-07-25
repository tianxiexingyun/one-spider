package org.chobit.spider.sites.epub;

import org.chobit.spider.process.src.Source;

/**
 * @author robin
 */
public class EpubSource implements Source {


    private final String baseUrl;

    public EpubSource(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public String indexUrl() {
        return null;
    }


    @Override
    public String baseUrl() {
        return this.baseUrl;
    }
}
