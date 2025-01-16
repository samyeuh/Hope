package Hope.controller.tool;

import Hope.controller.feedback.FeedbackService;
import Hope.controller.home.HomeService;
import Hope.exceptions.UnauthorizedActionException;
import Hope.model.Tool;
import Hope.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class ToolController {

    private final ToolService toolService;
    private final FeedbackService feedbackService;
    private final HomeService homeService;

    @Autowired
    public ToolController(ToolService toolService, FeedbackService feedbackService, HomeService homeService) {
        this.toolService = toolService;
        this.feedbackService = feedbackService;
        this.homeService = homeService;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }

    @GetMapping("/mainData")
    public String showAllMainData(Model model) {
        List<Tool> dataList = toolService.getAllMainTool();
        model.addAttribute("dataList", dataList);
        return "home";
    }

    @GetMapping("/details/{id}")
    public String getDataById(@PathVariable int id, Model model, Principal principal) {

        User user = homeService.getUser(principal.getName());
        if (user == null) {
            return "redirect:/login";
        }

        Tool data = toolService.getTool(id);
        model.addAttribute("data", data);
        model.addAttribute("comments", feedbackService.getComments(id));
        model.addAttribute("isAdmin", user.getRole().equals("admin"));

        return "details";
    }

    @GetMapping("/update/{id}")
    public String updateDataById(@PathVariable int id, Model model, Principal principal) {
        User user = homeService.getUser(principal.getName());
        if (!user.getRole().equals("admin")) {
            throw new UnauthorizedActionException("You are not allowed to update this tool");
        }
        Tool data = toolService.getTool(id);
        model.addAttribute("data", data);

        return "update";
    }

    @PostMapping("/updateData/{id}")
    public String updateData(@PathVariable int id, @ModelAttribute Tool dataObj, Principal principal) {
        User user = homeService.getUser(principal.getName());
        if (!user.getRole().equals("admin")) {
            throw new UnauthorizedActionException("You are not allowed to update this tool");
        }
        dataObj.setId(id);
        toolService.updateTool(dataObj);

        return "redirect:/details/" + id;
    }

    @GetMapping("/delete/{id}")
    public String deleteDataById(@PathVariable int id, Principal principal) {
        User user = homeService.getUser(principal.getName());
        if (!user.getRole().equals("admin")) {
            throw new UnauthorizedActionException("You are not allowed to update this tool");
        }
        toolService.deleteTool(id);

        return "redirect:/mainData";
    }

    @GetMapping("/addElement")
    public String addElementPage(Model model, Principal principal){
        User user = homeService.getUser(principal.getName());
        if (!user.getRole().equals("admin")) {
            throw new UnauthorizedActionException("You are not allowed to update this tool");
        }
        Tool tool = new Tool();
        model.addAttribute("newTool", tool);
        return "addElement";
    }

    @PostMapping("/submissionAddElement")
    public String addElement(@ModelAttribute("newTool") Tool tool){
        toolService.addTool(tool);

        return "redirect:/home";
    }
}
