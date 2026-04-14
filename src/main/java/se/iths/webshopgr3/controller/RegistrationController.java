package se.iths.webshopgr3.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.iths.webshopgr3.model.AppUser;
import se.iths.webshopgr3.service.AppUserService;

@Controller
@RequestMapping("/register")
@AllArgsConstructor
public class RegistrationController {

    private final AppUserService appUserService;

    @GetMapping
    public String getRegistrationForm(Model model) {
        model.addAttribute("user", new AppUser());
        return "register";
    }

    @PostMapping
    public String addUser(@ModelAttribute AppUser user) {
        appUserService.saveUser(user);
        return "redirect:/start";
    }

}
