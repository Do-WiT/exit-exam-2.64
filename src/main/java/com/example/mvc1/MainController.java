package com.example.mvc1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // Control
public class MainController { // control all request between view and control

    @GetMapping("")
    public String showPage(Model model){
        model.addAttribute(new Register());
        return "index";
    }

    @GetMapping("/process") // get binary file form view and throw to control
    public String process(@RequestParam("order") String order, Model model){
        Register register = Service.process(order);
        model.addAttribute(register);
        return "index";
    }

}
