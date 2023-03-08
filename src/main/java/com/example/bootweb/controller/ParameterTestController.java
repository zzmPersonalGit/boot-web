package com.example.bootweb.controller;

import com.example.bootweb.bean.Person;
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

    /*矩阵变量
     语法 /cars/sell;low=34;brand=byd,auid,yd
    springboot默认禁用了矩阵功能
    收到开启，对于路径得处理 UrlPathHelper
    removeSemicolonContent（移除分号内容）支持矩阵变量得内容
    矩阵变量必须有url路径变量才能被解析
    */
    @GetMapping("/cars/{path}")
    public Map<String, Object> carsSell(@MatrixVariable("low") Integer low,
                                        @MatrixVariable("brand") List<String> brand,
                                        @PathVariable("path") String path){
        Map<String,Object> map = new HashMap<>();
        map.put("low",low);
        map.put("brand",brand);
        map.put("path",path);
        return map;
    }

    ///boss/1;age=20/2;age=10
    @GetMapping("/boss/{bossId}/{empId}")
    public Map<String,Object> boss(@MatrixVariable(value = "age",pathVar = "bossId") Integer bossAge,
                                   @MatrixVariable(value = "age",pathVar = "empId") Integer empAge){
        Map<String,Object> map = new HashMap<>();
        map.put("bossAge",bossAge);
        map.put("empAge",empAge);
        return map;
    }


    /**
     * 数据绑定，页面提交数据（GET，POST）都可以和对象进行绑定
     * @param person
     * @return
     */
    @PostMapping("/saveuser")
    public Person saveuser(Person person){
        return person;
    }
}
