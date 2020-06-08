package pgt.goal.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class Controller {
    @GetMapping("/")
    public String helloWorld() {
        log.info("Called HelloWorld");
        return "Hello World";
    }
}