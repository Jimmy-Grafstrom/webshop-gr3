package se.iths.webshopgr3.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import se.iths.lw.mailfunctionlibrary.service.MessageService;
import se.iths.webshopgr3.model.AppUser;
import se.iths.webshopgr3.service.AppUserService;
import java.security.Principal;

@Controller
@AllArgsConstructor
@RequestMapping("/user")
public class AppUserController {

    private final AppUserService appUserService;
    private final MessageService messageService;



    @GetMapping("/profile")
    public String showProfile(@AuthenticationPrincipal UserDetails currentUser, Model model){
            String username = currentUser.getUsername();
            AppUser appUser =appUserService.findByUsername(username);
            model.addAttribute("username", appUser.getUsername());
            model.addAttribute("password",appUser.getPassword());
            model.addAttribute("cookies", appUser.isConsent());
            model.addAttribute("role", appUser.getRole());

            return "profile";
    }

    @PostMapping("/delete")
    public String deleteUser(Principal principal) {
        String username = principal.getName();
        appUserService.deleteUser(username);
        return "redirect:/logout";
    }

    @PostMapping("/profile/email")
    public String sendProfileToEmail(@AuthenticationPrincipal UserDetails currentUser,
                                     RedirectAttributes redirectAttributes){


        String username = currentUser.getUsername();
        appUserService.sendAccountInfoToEmail(username);

        redirectAttributes.addFlashAttribute("successMessage",
                "Account information has been sent to your E-mail.");

        return "redirect:/user/profile";
    }

}
