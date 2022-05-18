package com.pet.sas.stringsandsquare.controller;

import com.pet.sas.stringsandsquare.calculation.SquareTask;
import com.pet.sas.stringsandsquare.model.SquareModel;
import com.pet.sas.stringsandsquare.model.TaskType;
import com.pet.sas.stringsandsquare.model.TypeModel;
import com.pet.sas.stringsandsquare.service.SquareService;
import com.pet.sas.stringsandsquare.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping(path = "/home/square")
public class SquareController {

    private final SquareService squareService;
    private final TypeService typeService;
    private final SquareTask squareTask;

    @Autowired
    public SquareController(SquareService squareService, TypeService typeService, SquareTask squareTask) {
        this.squareService = squareService;
        this.typeService = typeService;
        this.squareTask = squareTask;
    }

    @PostMapping(params = "savesquare")
    public String saveNewSquareTask(@ModelAttribute("square") SquareModel squareModel,
                                    @ModelAttribute("type") TypeModel typeModel, Model model) {
        typeModel.setType(TaskType.SQUARE);
        typeService.saveType(typeModel);
        squareModel.setId(typeModel.getId());
        squareModel.setDate(LocalDate.now());
        squareService.saveSquare(squareModel);
        return "redirect:/home/square";
    }

    @PostMapping(params = "squaretask")
    public String calculateCost(@ModelAttribute("square") SquareModel squareModel, Model model) {
        model.addAttribute("result", squareTask.costOfMagicSquare(squareModel));
        return "square-page";
    }
}
