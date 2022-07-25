package org.chobit.spider;

import org.chobit.spider.process.SpiderProcessor;
import org.chobit.spider.process.sink.DownloadSink;
import org.chobit.spider.sites.hentai.HentaiSource;
import org.chobit.spider.sites.hentai.HentaiTransformer;
import org.junit.Test;

/**
 * @author robin
 */
public class HentaiTest {

    @Test
    public void process() {
        String indexUrl = "https://www.hentai.name/g/154371/";
        String baseUrl = "https://www.hentai.name/";
        String localPath = "/zhy/tomorrow/";

        HentaiSource source = new HentaiSource(indexUrl, baseUrl);
        HentaiTransformer transformer = new HentaiTransformer();
        DownloadSink sink = new DownloadSink(localPath);

        SpiderProcessor processor = new SpiderProcessor(source, transformer, sink);
        processor.process();
    }
    //-----------------------
}
