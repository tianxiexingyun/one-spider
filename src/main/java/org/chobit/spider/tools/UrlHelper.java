package org.chobit.spider.tools;

import org.apache.http.HttpHost;
import org.chobit.spider.except.InvalidUrlException;

import static org.chobit.common.constants.Symbol.SLASH;
import static org.chobit.common.utils.StrKit.isBlank;
import static org.chobit.spider.Config.PROXY_HOST;
import static org.chobit.spider.Config.PROXY_PORT;
import static org.chobit.spider.constants.Constants.FLAG_HTTP;
import static org.chobit.spider.constants.Constants.FLAG_HTTPS;

/**
 * @author robin
 */
public final class UrlHelper {


    /**
     * 拼接请求路径
     *
     * @param href 请求路径
     * @return 请求路径
     */
    public static String buildUrl(String href, String baseUrl) {
        if (href.startsWith(FLAG_HTTP) || href.startsWith(FLAG_HTTPS)) {
            return href;
        }
        if (isBlank(baseUrl)) {
            throw new InvalidUrlException(href);
        }

        if (href.startsWith(SLASH) && baseUrl.endsWith(SLASH)) {
            return baseUrl + href.substring(1);
        } else if (!href.startsWith(SLASH) && !baseUrl.endsWith(SLASH)) {
            return baseUrl + SLASH + href;
        } else {
            return baseUrl + href;
        }
    }






    private UrlHelper() {
    }
}
