package de.predic8.helden.api;

import de.predic8.helden.service.Greeter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    final Greeter greeter;

    public HelloWorldController(Greeter greeter) {
        this.greeter = greeter;
    }

    @RequestMapping("/hello")
    public String home(){
        return greeter.getGreeting();
    }

    @GetMapping("/hello-secure")
    public String secure(){
        return "Hallo " + SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
