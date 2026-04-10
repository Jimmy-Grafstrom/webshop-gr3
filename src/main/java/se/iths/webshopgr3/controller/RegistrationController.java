package se.iths.webshopgr3.controller;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.iths.webshopgr3.model.AppUser;
import se.iths.webshopgr3.repository.AppUserRepository;
import se.iths.webshopgr3.service.AppUserService;

@Controller
@RequestMapping("/register")
@AllArgsConstructor
public class RegistrationController {

    private AppUserService appUserService;

    @GetMapping
    private String getRegistrationForm(Model model) {
        model.addAttribute("user", new AppUser());
        return "register";
    }
}
