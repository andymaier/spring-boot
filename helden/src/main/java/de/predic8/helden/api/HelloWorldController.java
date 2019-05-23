package de.predic8.helden.api;

import de.predic8.helden.service.Greeter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

  @Autowired
  Greeter greeter;

  @RequestMapping("hello")
  public String home() {
    return greeter.getGreeting();
  }
}
