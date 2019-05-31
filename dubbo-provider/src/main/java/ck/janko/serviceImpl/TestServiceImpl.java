package ck.janko.serviceImpl;

import ck.janko.service.TestService;
import com.alibaba.dubbo.config.annotation.Service;
import ck.janko.domain.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


@Service(version = "1.0.0")
public class TestServiceImpl  implements  TestService {

    public String sayHello(String str) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return dateFormat.format(new Date()) + ": " + str;
    }


    public User findUser() {
        User user = new User();
        user.setId(1001);
        user.setUsername("scott");
        user.setPassword("tiger");
        user.setAge(20);
        user.setGender(0);
        return user;
    }

    /**
     * 服务生产者做的 关键方法熔断测试
     * @param str 参数名称
     * @return
     */
    @HystrixCommand(commandProperties = {
    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2500") })
    public Map<String, Object> hystrixTest(String str) {
        //判断熔断的最少请求数，默认是10；只有在一个统计窗口内处理的请求数量达到这个阈值，才会进行熔断与否的判断
        //hystrix超时配置的为2s,当实现类睡眠超过2s，服务调用者将执行服务降级函数
        int nextInt = new Random().nextInt(4000);
        System.out.println("sleep " + nextInt + "ms");
        try {
            Thread.sleep(nextInt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("code", 200);
        map.put("info", "Hello,"+str);
        return map;
    }
}
