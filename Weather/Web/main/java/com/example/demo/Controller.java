package com.example.demo;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.awt.*;
import java.io.IOException;

@RestController
    public class Controller {
        @GetMapping("/hello")
        public String hello(@RequestParam(required = false) String name) {
            return "Hello, " + name;
        }
    @RequestMapping("/home")
    public ModelAndView modelAndView(){
        return new ModelAndView("index");
    }


    @GetMapping("/3")
    public String index1(ModelMap map) {
        return "resources/templates/pillar1.jps";
    }



}
