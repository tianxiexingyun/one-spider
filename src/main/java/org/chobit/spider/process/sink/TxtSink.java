package org.chobit.spider.process.sink;

import org.chobit.spider.bean.PostContent;
import org.chobit.spider.bean.Volume;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.chobit.common.constants.Symbol.NEW_LINE;
import static org.chobit.common.utils.StrKit.isBlank;
import static org.chobit.common.utils.StrKit.isNotBlank;

/**
 * @author robin
 */
public class TxtSink implements Sink {


    private static final Logger logger = LoggerFactory.getLogger(TxtSink.class);


    private final String path;


    public TxtSink(String path) {
        this.path = path;
    }


    @Override
    public void sink(List<PostContent> contents) {

        List<Volume> volumes = tidyCatalog(contents);

        try (FileWriter writer = new FileWriter(path)) {
            for (Volume v : volumes) {
                writeVolume(v, writer);
            }
            writer.write(NEW_LINE + "END.");
        } catch (Exception e) {
            logger.error("Write to txt failed.", e);
        }
    }


    private void writeVolume(Volume v, FileWriter writer) throws IOException {
        if (isNotBlank(v.getName())) {
            writer.write(v.getName());
            writer.write(NEW_LINE + NEW_LINE);
        }
        for (PostContent pc : v.getChapters()) {
            if (pc.getLines().isEmpty()) {
                continue;
            }
            writer.write(NEW_LINE);
            writer.write(pc.getChapterName());
            writer.write(NEW_LINE);
            for (String line : pc.getLines()) {
                if (isBlank(line)) {
                    continue;
                }
                writer.write(line + NEW_LINE);
            }
            System.out.println(pc.getChapterName());
        }
    }
    // --------------------------------------------------------
}
