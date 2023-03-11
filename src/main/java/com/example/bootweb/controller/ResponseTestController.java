package com.example.bootweb.controller;

import com.example.bootweb.bean.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class ResponseTestController {

    /**
     * 浏览器发送请求直接返回xml [application/xml]
     * ajax请求 返回json      [application/json]
     * 应用客户端请求返回自定义协议数据  [application/]
     *
     * 步骤
     * 添加自定义MessageConverter进入系统底层
     * 系统底层统计出所有MessageConverter能操作哪些类型
     * 客户端协商
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/test/person")
    public Person getPerson(){
        Person person = new Person();
        person.setAge(24);
        person.setBirth(new Date());
        person.setUserName("张三");
        return person;
    }
}
