package ck.janko.controller;

import ck.janko.common.UploadFileCommon;
import ck.janko.domain.User;
import ck.janko.service.TestService;
import cn.hutool.core.io.resource.InputStreamResource;
import cn.hutool.http.HttpUtil;
import com.alibaba.dubbo.config.annotation.Reference;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Api(value = "用户接口")
@RestController
@RequestMapping("/")
public class TestController {

    /**
     * 引入服务提供者
     */
    @Reference(version = "1.0.0")
    private TestService testService;

    @GetMapping("hello")
    public String hello() {
        return testService.sayHello("Hello springboot and dubbo!");
    }


    @ApiOperation(value = "获取用户", notes = "根据id查询用户信息")
    @GetMapping("user")
    public User user() {
        return testService.findUser();
    }

    @ApiOperation(value = "单个方法测试熔断", notes = "测试熔断机制 2秒之内 10个请求")
    @GetMapping("/hystrixTest")
    @HystrixCommand(fallbackMethod = "hystrixTestFailBack")
    public Map<String,Object> hystrixTest(String name) {
        return testService.hystrixTest(name);
    }

    private Map<String,Object>  hystrixTestFailBack(String name) {
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("code", 500);
        map.put("info", "系统出错，稍后重试");
        return map;
    }


    @ApiOperation(value = "上传文件", notes = "上传文件")
    @PostMapping("/upload")
    public String uploadFileTest(MultipartFile file) {
        return UploadFileCommon.uploadFile(file,"http://10.1.11.68:8080/upload");
//        String result = "";
//        try {
//            InputStreamResource isr = new InputStreamResource(file.getInputStream(),file.getOriginalFilename());
//
//            Map<String, Object> params = new HashMap<>();
//            params.put("file", isr);
//            params.put("path", "86501729");
//            params.put("output", "json");
//            String resp = HttpUtil.post("http://10.1.11.68:8080/upload", params);
//
//            result = resp;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return result;
    }
}
