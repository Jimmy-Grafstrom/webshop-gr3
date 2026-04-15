package se.iths.webshopgr3.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.iths.webshopgr3.service.AppUserService;

import java.security.Principal;

@Controller
@AllArgsConstructor
@RequestMapping("/user")
public class AppUserController {

    private final AppUserService appUserService;

    @PostMapping("/delete")
    public String deleteUser(@RequestParam String username, Principal principal) {
        if (!principal.getName().equals(username)){
            throw new RuntimeException("You can only delete your own account.");
        }
        appUserService.deleteUser(username);
        return "redirect:/logout";
    }

}
