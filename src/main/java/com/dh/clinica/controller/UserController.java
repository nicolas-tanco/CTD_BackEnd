package com.dh.clinica.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/admin")
    public String entrar(){
        return "<h1>Hola!</h1>";
    }
    @GetMapping("/")
    public String entrar1(){
        return "<h1>Hola!</h1>";
    }
    @GetMapping("/user")
    public String entrar2(){
        return "<h1>Hola!</h1>";
    }
}
