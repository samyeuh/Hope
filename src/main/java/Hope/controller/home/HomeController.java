package Hope.controller.home;

import Hope.model.DataHope;
import Hope.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    private final HomeService homeService;

    @Autowired
    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @RequestMapping("/home")
    public String home(Principal principal, Model model) {
        if (principal != null) {
            User user = homeService.getUser(principal.getName());
            model.addAttribute("username", user.getFirstName() + " " + user.getLastName());
        } else {
            return "redirect:/login";
        }

        List<DataHope> dataList = homeService.getPreviewsData();
        model.addAttribute("dataList", dataList);

        return "home";
    }

    @GetMapping("/search")
    public String search(
            @RequestParam("query") String query,
            Principal principal,
            Model model
    ) {
        if (principal != null) {
            User user = homeService.getUser(principal.getName());
            model.addAttribute("username", user.getFirstName() + " " + user.getLastName());
        } else {
            return "redirect:/login";
        }

        if (query == null || query.isEmpty()) {
            return "redirect:/home";
        }

        List<DataHope> searchResults = homeService.searchData(query);
        model.addAttribute("dataList", searchResults);
        model.addAttribute("query", query);

        return "home";
    }
}
