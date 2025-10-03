package br.com.puctech.battle_arena.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {


    @GetMapping("/")
    public String helloWorld() {
        return "Hello World! Hello Spring!";
    }

    @GetMapping("/{name}")
    public String sayMyName(@PathVariable(name = "name") String name) {
        return "Hello " + name + "!";
    }
}
