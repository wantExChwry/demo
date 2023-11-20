package com.example.demo.git;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 吴荣洋
 * @program: demo
 * @description:
 * @date 2023-11-20:55:27
 */

@RestController
@Slf4j
@RequestMapping("/get")
public class gitCheck {
    @RequestMapping("/check")
    public String login(){
        return "check";
    }
}