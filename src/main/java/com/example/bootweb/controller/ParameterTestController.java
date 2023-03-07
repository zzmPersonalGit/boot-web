package com.example.bootweb.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ParameterTestController {

    /*
    * @PathVariable 注解获取请求路径上的id值，值来源于路径变量
    * "/car/{id}"REST请求方式，在请求路径上添加路径变量处理不同的Car请求
    * @RequestHeader("User-Agent") String userAgent 带参即获取请求头某一个参数，不带参即获取请求头所有参数
    * @RequestParam("age") Integer age 获取请求参数的单个指定值
    * @RequestParam Map<String,String> params 获取请求参数所有值
    * @CookieValue("Idea-775bb6e9") String ga 获取指定cookie值
    * @CookieValue("Idea-775bb6e9") Cookie cookie 获取指定名字的cookie对象
    */
    @GetMapping("/car/{id}/owner/{username}")
    public Map<String,Object> getCar(@PathVariable Integer id,
                                     @PathVariable String username,
                                     @PathVariable Map<String,String> pv,
                                     @RequestHeader("User-Agent") String userAgent,
                                     @RequestHeader Map<String,String> header,
                                     @RequestParam("age") Integer age,
                                     @RequestParam("inters") List<String> inters,
                                     @RequestParam Map<String,String> params,
                                     @CookieValue("Idea-775bb6e9") String ga,
                                     @CookieValue("Idea-775bb6e9") Cookie cookie){
        Map<String,Object> map = new HashMap<>();
       /* map.put("id",id);
        map.put("name",username);
        map.put("pv",pv);
        map.put("userAgent",userAgent);
        map.put("headers",header)*/;
        map.put("age",age);
        map.put("inters",inters);
        map.put("params",params);
        map.put("_ga",ga);
        map.put("cookie",cookie);
        System.out.println(cookie);
        System.out.println(ga);
        return map;
    }


    /*
    * @RequestBody String content 获取请求体，只有post请求才有请求体，表单提交可以获取表单里所有内容
    * */
    @PostMapping("/save")
    public Map postMethord(@RequestBody String content){
        Map<String,Object> map = new HashMap<>();
        map.put("content",content);
        return map;
    }
}
