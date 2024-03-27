package ru.tensors.Tensors.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping("/unary_operations")
    private String get() {
        return "unary_operations";
    }
}