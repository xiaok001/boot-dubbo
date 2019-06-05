package ck.janko.common;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.io.resource.InputStreamResource;
import cn.hutool.core.lang.Console;
import cn.hutool.http.HttpUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UploadFileCommon {

    public static void main(String[] args) {
        test();
    }

    public static String test() {
        String result = null;
        try {
            //文件地址
            File file = new File("D:\\美女2.jpg");
            //声明参数集合
            HashMap paramMap = new HashMap<String,Object>();
            //文件
            paramMap.put("file", file);
            //输出
            paramMap.put("output", "json");
            //自定义路径
            paramMap.put("path", "image");
            //场景
//            paramMap.put("scene", "image");
            System.out.println(DateTime.now());
            //上传
            result = HttpUtil.post("http://10.1.11.68:8080/upload", paramMap);
//            result = HttpUtil.post("http://10.1.11.68:9999/big/upload", paramMap);//使用断点续传的功能需要更改为当前的url
            System.out.println(DateTime.now());
            //输出json结果
            System.out.println("添加结果是：" + result);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            result = null;
        }
        return result;
    }

    /**
     * 上传文件
     *
     * @param file       上传的文件
     * @param uploadPath 上传文件路径
     * @return
     */
    public static String uploadFile(MultipartFile file, String uploadPath) {
        String result = "";
        try {
            InputStreamResource isr = new InputStreamResource(file.getInputStream(), file.getOriginalFilename());
            Map<String, Object> params = new HashMap<String,Object>();
            params.put("file", isr);
            params.put("path", "file");
            params.put("output", "json");
            String resp = HttpUtil.post(uploadPath, params);
            Console.log("resp: {}", resp);
            result = resp;
        } catch (IOException e) {
            e.getMessage();
        }
        return result;
    }
}
