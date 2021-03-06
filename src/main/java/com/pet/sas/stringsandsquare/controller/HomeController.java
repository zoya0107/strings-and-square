package com.pet.sas.stringsandsquare.controller;

import com.pet.sas.stringsandsquare.model.SquareModel;
import com.pet.sas.stringsandsquare.model.StringsModel;
import com.pet.sas.stringsandsquare.service.SquareService;
import com.pet.sas.stringsandsquare.service.StringsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/home")
public class HomeController {

    private final SquareService squareService;
    private final StringsService stringsService;

    @Autowired
    public HomeController(SquareService squareService, StringsService stringsService) {
        this.squareService = squareService;
        this.stringsService = stringsService;
    }

    @GetMapping
    public String getHomePage(Model model) {
        model.addAttribute("square", new SquareModel());
        model.addAttribute("strings", new StringsModel());
        return "home-page";
    }

    @GetMapping("/square")
    public String getPageOne(Model model) {
        model.addAttribute("square", new SquareModel());
        model.addAttribute("resultSquare", new SquareModel());
        model.addAttribute("listSquares", squareService.getSquaresList());
        return "square-page";
    }

    @GetMapping("/strings")
    public String getPageTwo(Model model) {
        model.addAttribute("strings", new StringsModel());
        model.addAttribute("listStrings", stringsService.getStringsList());
        return "strings-page";
    }
}
