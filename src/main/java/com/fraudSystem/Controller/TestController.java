package com.fraudSystem.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "This is the test of project";
    }

    @GetMapping("/secure")
    public String secure(){
        return "This is the protected api";
    }

}
