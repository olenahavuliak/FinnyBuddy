package com.example.finnybuddy.domain.budget.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class Test {
    @GetMapping("/controller")
    public String foo() {
        return "Response!";
    }
}
