package org.engripaye.iamsystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

        @GetMapping("/test")
        public String testAdminAccess() {
            return "Hello Admin, Access Granted!";
        }

}
