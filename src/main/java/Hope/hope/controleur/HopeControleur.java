package Hope.hope.controleur;

import Hope.hope.model.DataHope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/hope")
public class HopeControleur {

    private final HopeService hopeService;

    @Autowired
    public HopeControleur(HopeService hopeService) {
        this.hopeService = hopeService;
    }

    @GetMapping("/")
    public String index() {
        return "loginPage";
    }

    @GetMapping("/mainData")
    public String showAllMainData(Model model) {
        List<DataHope> dataList = hopeService.getAllMainData();
        model.addAttribute("dataList", dataList);

        return "home";
    }

    @GetMapping("/addElement")
    public String addElementPage(Model model){
        DataHope dataHope = new DataHope();
        model.addAttribute("newDataHope", dataHope);
        return "addElement";
    }

    @PostMapping("/submissionAddElement")
    public String addElement(@ModelAttribute("newDataHope") DataHope dataHope){
        hopeService.addElement(dataHope);

        return "redirect:/hope/mainData";
    }
}
