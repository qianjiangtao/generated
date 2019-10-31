package com.generated.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * TODO
 *
 * @author Gent
 * @since 2019/9/29 09:18
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public Object hello() {
        return "Hello Generator";
    }
}
