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

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.io.IOUtils.copy;

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
        model.addAttribute("listSquares", squareService.getSquaresList());
        return "redirect:/home/square";
    }

    @PostMapping(params = "calculate")
    public String calculateCost(@ModelAttribute("square") SquareModel squareModel, Model model) {
        List<Integer> check = squareTask.toList(squareModel);
        List<Integer> res = squareTask.getBestOfMagicSquare(squareModel);
        model.addAttribute("resultSquare", squareTask.toSquare(res));
        model.addAttribute("result", squareTask.countCost(check, res));
        model.addAttribute("listSquares", squareService.getSquaresList());
        return "square-page";
    }

    @PostMapping(params = "semi-calculate")
    public String calculateSemiCost(@ModelAttribute("square") SquareModel squareModel, Model model) {
        List<Integer> check = squareTask.toList(squareModel);
        List<Integer> res = squareTask.getBestOfSemiMagicSquare(squareModel);
        model.addAttribute("resultSquare", squareTask.toSquare(res));
        model.addAttribute("result", squareTask.countCost(check, res));
        model.addAttribute("listSquares", squareService.getSquaresList());
        return "square-page";
    }

    @PostMapping(params = "export")
    public void exportSquareToJsonFile(@ModelAttribute("square") SquareModel squareModel,
                                       @ModelAttribute("type") TypeModel typeModel,
                                       HttpServletResponse response) {
        typeModel.setType(TaskType.SQUARE);

        response.setContentType("application/json");
        response.setHeader("Content-Disposition", "attachment; filename=\"square.json\"");

        File file = new File("square.json");
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

        try {
            InputStream is = new FileInputStream(file);
            copy(is, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException ex) {
            throw new RuntimeException("IOError writing file to output stream");
        }
    }

    @GetMapping("/{id}")
    public String chooseSquare(@PathVariable(value = "id") Long id, Model model) {
        SquareModel squareModel = squareService.getSquareById(id);
        model.addAttribute("square", squareModel);
        model.addAttribute("resultSquare", new SquareModel());
        model.addAttribute("listSquare", squareService.getSquaresList());
        return "square-page";
    }
}
