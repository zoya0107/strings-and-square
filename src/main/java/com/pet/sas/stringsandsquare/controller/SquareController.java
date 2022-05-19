package com.pet.sas.stringsandsquare.controller;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pet.sas.stringsandsquare.calculation.SquareTask;
import com.pet.sas.stringsandsquare.model.SquareModel;
import com.pet.sas.stringsandsquare.model.TaskType;
import com.pet.sas.stringsandsquare.model.TypeModel;
import com.pet.sas.stringsandsquare.service.SquareService;
import com.pet.sas.stringsandsquare.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

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

    @PostMapping(params = "save")
    public String saveNewSquareTask(@ModelAttribute("square") SquareModel squareModel,
                                    @ModelAttribute("type") TypeModel typeModel, Model model) {
        typeModel.setType(TaskType.SQUARE);
        typeService.saveType(typeModel);
        squareModel.setId(typeModel.getId());
        squareModel.setDate(LocalDate.now());
        squareService.saveSquare(squareModel);
        return "redirect:/home/square";
    }

    @PostMapping(params = "calculate")
    public String calculateCost(@ModelAttribute("square") SquareModel squareModel, Model model) {
        model.addAttribute("result", squareTask.costOfMagicSquare(squareModel));
        return "square-page";
    }

    @PostMapping(params = "export")
    public String exportTheSquare(@ModelAttribute("square") SquareModel squareModel,
                                  @ModelAttribute("type") TypeModel typeModel) {
        typeModel.setType(TaskType.SQUARE);
        File file = new File("target/square.json");
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> taskMap = new HashMap<>();
        taskMap.put("numbers", squareModel);
        taskMap.put("task", typeModel);
        try {
            JsonGenerator g = objectMapper.getFactory().createGenerator(new FileOutputStream(file));
            objectMapper.writeValue(g, taskMap);
            g.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "square-page";
    }

    @GetMapping("/{id}")
    public String solveSquare(@PathVariable(value = "id") Long id, Model model) {
        SquareModel squareModel = squareService.getSquareById(id);
        model.addAttribute("square", squareModel);
        model.addAttribute("listSquare", squareService.getSquaresList());
        return "square-page";
    }
}
