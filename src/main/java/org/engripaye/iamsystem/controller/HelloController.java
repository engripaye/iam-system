package org.engripaye.iamsystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/user/hello")
    public String helloUser() {
        return "Hello User!";
    }

    @GetMapping("/admin/hello")
    public String helloAdmin(){
        return "Hello Admin!";
    }
}
