package Hope.controller;

import Hope.model.DataHope;
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
        List<DataHope> dataList = hopeService.getAllMainData();
        model.addAttribute("dataList", dataList);
        return "home";
    }

    @GetMapping("/details/{id}")
    public String getDataById(@PathVariable int id, Model model) {
        Optional<DataHope> data = hopeService.getData(id);
        if (data.isPresent()) {
            DataHope dataObj = data.get();
            model.addAttribute("data", dataObj);
        }

        return "details";
    }

}
