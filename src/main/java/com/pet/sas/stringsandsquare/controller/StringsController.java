package com.pet.sas.stringsandsquare.controller;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pet.sas.stringsandsquare.calculation.StringsTask;
import com.pet.sas.stringsandsquare.model.StringsModel;
import com.pet.sas.stringsandsquare.model.TaskType;
import com.pet.sas.stringsandsquare.model.TypeModel;
import com.pet.sas.stringsandsquare.service.StringsService;
import com.pet.sas.stringsandsquare.service.TypeService;
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
@RequestMapping(path = "/home/strings")
public class StringsController {

    private final StringsService stringsService;
    private final TypeService typeService;
    private final StringsTask stringsTask;

    public StringsController(StringsService stringsService, TypeService typeService, StringsTask stringsTask) {
        this.stringsService = stringsService;
        this.typeService = typeService;
        this.stringsTask = stringsTask;
    }

    @PostMapping(params = "save")
    public String saveNewStringsTask(@ModelAttribute("strings") StringsModel stringsModel,
                                     @ModelAttribute("type") TypeModel typeModel, Model model) {
        typeModel.setType(TaskType.STRINGS);
        typeService.saveType(typeModel);
        stringsModel.setId(typeModel.getId());
        stringsModel.setDate(LocalDate.now());
        stringsService.saveStrings(stringsModel);
        model.addAttribute("listStrings", stringsService.getStringsList());
        return "redirect:/home/strings";
    }

    @PostMapping(params = "calculate")
    public String filterTheStrings(@ModelAttribute("strings") StringsModel stringsModel, Model model) {
        model.addAttribute("result", stringsTask.getSubstrings(stringsModel));
        model.addAttribute("listStrings", stringsService.getStringsList());
        return "strings-page";
    }

    @PostMapping(params = "export")
    public String exportTheStrings(@ModelAttribute("strings") StringsModel stringsModel,
                                   @ModelAttribute("type") TypeModel typeModel, Model model) {
        typeModel.setType(TaskType.STRINGS);
        File theDir = new File("C://data_sas");
        if (!theDir.exists()) {
            theDir.mkdirs();
        }
        File file = new File(theDir, "strings.json");
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> taskMap = new HashMap<>();
        taskMap.put("strings", stringsModel);
        taskMap.put("task", typeModel);
        try {
            JsonGenerator g = objectMapper.getFactory().createGenerator(new FileOutputStream(file));
            objectMapper.writeValue(g, taskMap);
            g.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        model.addAttribute("listStrings", stringsService.getStringsList());
        return "strings-page";
    }

    @GetMapping("/{id}")
    public String solveStrings(@PathVariable(value = "id") Long id, Model model) {
        StringsModel stringsModel = stringsService.getStringsById(id);
        model.addAttribute("strings", stringsModel);
        model.addAttribute("listStrings", stringsService.getStringsList());
        return "strings-page";
    }
}
