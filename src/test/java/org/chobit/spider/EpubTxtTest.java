package org.chobit.spider;

import org.chobit.spider.process.SpiderProcessor;
import org.chobit.spider.process.sink.TxtSink;
import org.chobit.spider.sites.epub.EpubSource;
import org.chobit.spider.sites.epub.EpubTxtTransformer;
import org.junit.Test;

/**
 * @author robin
 */
public class EpubTxtTest {

    @Test
    public void process() {
        String baseUrl = "http://localhost:82/";
        String localPath = "/zhy/roar-woman.txt";
        String indexPath = "/toc.ncx";

        EpubSource source = new EpubSource(baseUrl);
        EpubTxtTransformer transformer = new EpubTxtTransformer(21, indexPath);
        TxtSink sink = new TxtSink(localPath);

        SpiderProcessor processor = new SpiderProcessor(source, transformer, sink);
        processor.process();
    }

}
