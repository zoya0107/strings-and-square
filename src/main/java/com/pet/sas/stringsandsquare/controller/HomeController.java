package com.pet.sas.stringsandsquare.controller;

import com.pet.sas.stringsandsquare.model.SquareModel;
import com.pet.sas.stringsandsquare.model.StringsModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/home")
public class HomeController {

    @GetMapping
    public String getHomePage(Model model) {
        model.addAttribute("square", new SquareModel());
        model.addAttribute("strings", new StringsModel());
        return "home-page";
    }

    @GetMapping("/square")
    public String getPageOne(Model model) {
        model.addAttribute("square", new SquareModel());
        return "square-page";
    }

    @GetMapping("/strings")
    public String getPageTwo(Model model) {
        model.addAttribute("strings", new StringsModel());
        return "strings-page";
    }
}
