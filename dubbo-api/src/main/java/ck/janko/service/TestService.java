package ck.janko.service;


import ck.janko.domain.User;

import java.util.Map;

public interface TestService {

    String sayHello(String str);

    User findUser();

    /**
     * 主要是做测试熔断器的
     * @param str 参数名称
     * @return
     */
    Map<String, Object> hystrixTest(String str);
}