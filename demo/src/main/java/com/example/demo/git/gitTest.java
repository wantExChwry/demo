package com.example.demo.git;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 吴荣洋
 * @program: demo
 * @description:
 * @date 2023-11-20:22:48
 */

@RestController
@Slf4j
@RequestMapping("/login")
public class gitTest {
    @RequestMapping("login")
    public String login(){
        return "login";
    }

    @RequestMapping("get")
    public String get(){
        return "login";
    }

    @RequestMapping("ge2t")
    public String getWx2(){
        return "getWx2";
    }
    @RequestMapping("ge3t")
    public String getWx3(){
        return "getWx3";
    }

    @RequestMapping("getLog")
    public String getLog(){
        System.out.println("//////////");
        return "getLog";
    }
}
