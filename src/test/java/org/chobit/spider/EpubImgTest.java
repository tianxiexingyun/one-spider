package org.chobit.spider;

import org.chobit.spider.process.SpiderProcessor;
import org.chobit.spider.process.sink.DownloadSink;
import org.chobit.spider.sites.epub.EpubImageTransformer;
import org.chobit.spider.sites.epub.EpubSource;
import org.junit.Test;

public class EpubImgTest {


    @Test
    public void process() {
        String baseUrl = "http://localhost:82/epub/html/";
        String home = "http://localhost:82/epub";
        String localPath = "/zhy/xiaoqian/";


        EpubSource source = new EpubSource(baseUrl);
        EpubImageTransformer transformer = new EpubImageTransformer(164, home);
        DownloadSink sink = new DownloadSink(localPath);

        SpiderProcessor processor = new SpiderProcessor(source, transformer, sink);
        processor.process();
    }

}
