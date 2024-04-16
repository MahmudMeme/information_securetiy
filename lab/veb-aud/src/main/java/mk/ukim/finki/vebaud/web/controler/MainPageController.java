package mk.ukim.finki.vebaud.web.controler;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.vebaud.model.User;
import mk.ukim.finki.vebaud.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("main")
public class MainPageController {
    private final UserService userService;

    public MainPageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    private String getMainPage(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user", user);
        model.addAttribute("users", userService.findAll());
        model.addAttribute("bodyContent", "main");
        return "master-template";
    }

    @GetMapping("/makeAnAdmin")
    private String makeSomeoneAnAdmin(Model model) {
        List<User> userList = userService.AllUsersWIhtRoleUser();
        model.addAttribute("users", userList);
        model.addAttribute("bodyContent", "makeAdmin");
        return "master-template";
    }

    @PostMapping("/addAdmin")
    private String makeAdmin(@RequestParam Long userId) {
        userService.makeAdmin(userId);
        return "redirect:/main";

    }
}
