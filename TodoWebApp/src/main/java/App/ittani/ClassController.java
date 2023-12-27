package App.ittani;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ClassController {

    @RequestMapping(value="/",method = RequestMethod.GET)
    public String welcomepage(Model model) {

        return "home";
    }

    @RequestMapping(value = "/todo", method = RequestMethod.GET)
    public String todoPage(Model model) {
        // Your logic
        return "todo"; // Assuming you have a todo.html template
    }
    @GetMapping("/backToWelcome")
    public String backToWelcomePage() {
        return "redirect:/login";  // assuming "homepage" is the URL to the home page
    }
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String homePage(Model model) {
        // Your logic
        return "home"; // Assuming you have a todo.html template
    }
}