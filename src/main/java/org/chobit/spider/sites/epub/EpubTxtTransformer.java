package org.chobit.spider.sites.epub;

/**
 * @author robin
 */
public class EpubTxtTransformer extends AbstractEpubTransformer {

    public EpubTxtTransformer(int total) {
        super(total);
    }

    @Override
    public String postUrlSelector() {
        return null;
    }

    @Override
    public String postTitleSelector() {
        return "";
    }

    @Override
    public String postContentSelector() {
        return "body > div";
    }
}
