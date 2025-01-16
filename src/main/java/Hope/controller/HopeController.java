package Hope.controller;

import Hope.model.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class HopeController {

    private final HopeService hopeService;

    @Autowired
    public HopeController(HopeService hopeService) {
        this.hopeService = hopeService;
    }

    @GetMapping("/")
    public String index() {
        // TODO: verifier si l'utilisateur est connecté
        return "redirect:/login";
    }

    @GetMapping("/mainData")
    public String showAllMainData(Model model) {
        List<Tool> dataList = hopeService.getAllMainData();
        model.addAttribute("dataList", dataList);
        return "home";
    }

    @GetMapping("/details/{id}")
    public String getDataById(@PathVariable int id, Model model) {
        Optional<Tool> data = hopeService.getTool(id);
        if (data.isPresent()) {
            Tool dataObj = data.get();
            model.addAttribute("data", dataObj);
        }

        return "details";
    }

    @GetMapping("/update/{id}")
    public String updateDataById(@PathVariable int id, Model model) {
        Optional<Tool> data = hopeService.getTool(id);
        if (data.isPresent()) {
            Tool dataObj = data.get();
            model.addAttribute("data", dataObj);
        }

        return "update";
    }

    @PostMapping("/updateData/{id}")
    public String updateData(@PathVariable int id, @ModelAttribute Tool dataObj) {
        dataObj.setId(id);
        hopeService.updateTool(dataObj);

        return "redirect:/details/" + id;
    }

    @GetMapping("/delete/{id}")
    public String deleteDataById(@PathVariable int id) {
        hopeService.deleteTool(id);

        return "redirect:/mainData";
    }

}
