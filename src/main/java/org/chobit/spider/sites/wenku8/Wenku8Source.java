package org.chobit.spider.sites.wenku8;

import org.chobit.spider.process.src.Source;

import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

/**
 * @author robin
 */
public class Wenku8Source implements Source {


    private final String indexUrl;

    private final String baseUrl;

    private final List<String> blacklist = new LinkedList<>();


    public Wenku8Source(String indexUrl, String baseUrl) {
        this.indexUrl = indexUrl;
        this.baseUrl = baseUrl;
    }

    
    public void addBlacklist(String key) {
        this.blacklist.add(key);
    }


    @Override
    public String indexUrl() {
        return this.indexUrl;
    }

    @Override
    public Charset charset() {
        return Charset.forName("GBK");
    }


    @Override
    public String baseUrl() {
        return this.baseUrl;
    }


    @Override
    public List<String> blacklist() {
        return this.blacklist;
    }
}
