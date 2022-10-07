package org.chobit.spider.sites.epub;

/**
 * @author robin
 */
public class EpubTxtTransformer extends AbstractEpubTransformer {

    public EpubTxtTransformer(int total, String indexPath) {
        super(total, indexPath);
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
