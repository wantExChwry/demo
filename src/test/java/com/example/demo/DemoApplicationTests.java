package com.example.demo;

import com.example.demo.controller.SendNoChange;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    SendNoChange helloRa;

    @Test
    void contextLoads() {
        helloRa.send();
    }

}
