package com.niuzhendong.boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BootController {

	@RequestMapping("/")
    public String greeting() {
        return "Hello World!";
    }
}
