package org.jianfengderek.cxfjaxrsdemo;

public class HelloServiceImpl implements HelloService {

    @Override
    public String hello() {
        return "Hello CXF jax-rs!";
    }

}
