package ru.tensors.Tensors.controllers;

import ch.qos.logback.core.model.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping("/")
    private String mainPage() {
        return "unary_operations";
    }

    @RequestMapping(value = "/set_tensor")
    public void setTensor() {
        System.out.println("Success");



    }
}