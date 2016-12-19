package hu.schonhertz.training.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// this is a controller
@Controller
// if a request enter on "/" url then this controller will handle it
@RequestMapping("/")
public class HelloController{
 
  // on GET http request this method will be run
  @RequestMapping(method = RequestMethod.GET)
  public String printHello(ModelMap model) {
    // model will be given by spring 
    model.addAttribute("message", "Hello Spring MVC Framework!");
    // Spring will redirect to /WEB-INF/jsp/hello/hello.jsp
    return "hello/hello";
  }

}
