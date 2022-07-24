package org.chobit.spider.except;

/**
 * @author robin
 */
public class InvalidUrlException extends RuntimeException {

    public InvalidUrlException(String url) {
        super("url [" + url + "] is invalid.");
    }
}
