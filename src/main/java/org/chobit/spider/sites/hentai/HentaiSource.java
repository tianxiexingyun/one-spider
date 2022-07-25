package org.chobit.spider.sites.hentai;

import org.chobit.spider.process.src.Source;

/**
 * @author robin
 */
public class HentaiSource implements Source {

    private final String indexUrl;

    private final String baseUrl;


    public HentaiSource(String indexUrl, String baseUrl) {
        this.indexUrl = indexUrl;
        this.baseUrl = baseUrl;
    }

    @Override
    public boolean useProxy() {
        return true;
    }

    @Override
    public String indexUrl() {
        return this.indexUrl;
    }

    @Override
    public String baseUrl() {
        return this.baseUrl;
    }
}
