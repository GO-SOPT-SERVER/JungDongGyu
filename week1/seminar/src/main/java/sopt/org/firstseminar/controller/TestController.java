package sopt.org.firstseminar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        System.out.println("TestController.test");
        return "<strong>Welcome!</strong>";
    }
}
