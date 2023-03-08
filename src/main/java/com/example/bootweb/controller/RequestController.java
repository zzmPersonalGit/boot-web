package com.example.bootweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class RequestController {

    @GetMapping("/goto")
    public String goToPage(HttpServletRequest request){
        request.setAttribute("msg","成功啦");
        request.setAttribute("code","200");

        return "forward:/success";
    }

    /*
    * 取出请求域中参数写法
    * @RequestAttribute(value = "msg",required = false) String msg  required = false参数表示该请求参数不是必须得
    *
    * */

    @ResponseBody
    @GetMapping("/success")
    public Map<String, Object> success(@RequestAttribute(value = "msg",required = false) String msg,
                                       @RequestAttribute(value = "code",required = false) String code,
                                       HttpServletRequest request){
        Object msg1 = request.getAttribute("msg");
        Object code1 = request.getAttribute("code");
        Map<String, Object> map = new HashMap<>();

        Object hello = request.getAttribute("hello");
        Object world = request.getAttribute("world");
        Object message = request.getAttribute("message");

        map.put("reqMethord_msg",msg1);
        map.put("annotation_msg",msg);
        map.put("hello",hello);
        map.put("world",world);
        map.put("message",message);

        return map;
    }

    /*
    *  cookie.setDomain cookie跨跨域设置
    * Map<String,Object> map,Model model,HttpServletRequest request,都可以给request域中放数据
    * */

    @GetMapping("/params")
    public String testParam(Map<String,Object> map,
                            Model model,
                            HttpServletRequest request,
                            HttpServletResponse response){
        map.put("hello","777");
        model.addAttribute("world","ghsuei2424");
        request.setAttribute("message","helloWorld");
        Cookie cookie = new Cookie("c1", "v1");
        cookie.setDomain("localhost");
        response.addCookie(cookie);
        return "forward:/success";
    }
}
