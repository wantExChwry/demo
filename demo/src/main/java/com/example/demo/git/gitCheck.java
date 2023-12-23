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
    @RequestMapping("/update")
    public String update1(){
        return "update";
    }
    @RequestMapping("/update2")
    public String update2(){
        return "update2";
    }
    @RequestMapping("/updateTst2")
    public String updateTset2(){
        System.out.println("cherry-pick");
        System.out.println("cherry-pick");
        System.out.println("cherry-pick");
        System.out.println("cherry-pick");
        System.out.println("a");
        System.out.println("b");
        System.out.println("cherry-pick");
        return "cherry-pick";
    }

    @RequestMapping("/rebase")
    public String rebase(){
        System.out.println("rebase develop");
        return "rebase";
    }

}
