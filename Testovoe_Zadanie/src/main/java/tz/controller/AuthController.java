package tz.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tz.dto.AuthUser;
import tz.service.TaskService;
import tz.service.UserService;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @GetMapping("/login")
    public String loginPage(@RequestParam(required = false) String error, Model model) {
        model.addAttribute("errorMessage", error);
        return "auth/login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("authUser",new AuthUser());
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute AuthUser user, BindingResult error) {
        if (error.hasErrors()){
            return "auth/register";
        }
        userService.create(user);
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logoutPage() {
        return "auth/logout";
    }

    @GetMapping("/home")
    public String homePage(Model model) {
        return "auth/home";
    }
}
