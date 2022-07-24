package org.chobit.spider;

import org.chobit.common.utils.PropKit;

import static org.chobit.common.utils.PropKit.*;

/**
 * @author robin
 */
public final class Config {


    static {
        PropKit.load("/config.properties");
    }


    /**
     * 代理地址
     */
    public static final String PROXY_HOST = getProp("proxy.host");

    /**
     * 代理端口号
     */
    public static final int PROXY_PORT = getInt("proxy.port");


    private Config(){}

}
