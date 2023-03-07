package com.example.bootweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    *
    *
    * */

    @ResponseBody
    @GetMapping("/success")
    public Map<String, Object> success(@RequestAttribute("msg") String msg,
                                       @RequestAttribute("code") String code,
                                       HttpServletRequest request){
        Object msg1 = request.getAttribute("msg");
        Object code1 = request.getAttribute("code");
        Map<String, Object> map = new HashMap<>();

        map.put("reqMethord_msg",msg1);
        map.put("annotation_msg",msg);

        return map;
    }
}
