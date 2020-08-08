package com.javabom.cafe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/menus")
    public String menuPage(){
        return "/menus.html";
    }


    @GetMapping("/tables")
    public String tablePage(){
        return "/table.html";
    }


    @GetMapping("/sales")
    public String salesPage(){
        return "/sales.html";
    }
}
