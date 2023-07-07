package com.mballem.curso.jasper.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Connection;


@Controller
public class HomeController {

    @Autowired
    private Connection connection;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/conn")
    public String myConn(Model model) {
        model.addAttribute("conn", connection != null ? "Conexão ok!!" : "Ops... sem conexão");
        System.out.println(model.toString());
        return "index";
    }

}
