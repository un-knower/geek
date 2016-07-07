package com.tzp.T.jee.rpc;

/**
 * 描述:服务Echo实现类
 */
public class EchoServiceImpl implements EchoService {
    @Override
    public String echo(String ping) {
        return ping != null ? ping + " --> I am ok." : "I am ok.";
    }
}
