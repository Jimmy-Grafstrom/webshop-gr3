package se.iths.webshopgr3.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.time.Duration;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class PublicController {


    @GetMapping
    public String start(HttpServletRequest request, Model model) {
        model.addAttribute("hasConsent", hasConsent(request));
        return "start";
    }

    private boolean hasConsent(HttpServletRequest request) {
        String consent = getCookieValue(request, "consentcookie");
        if (consent != null && consent.equals("true")) {
            return true;
        }
        return false;
    }

    private String getCookieValue(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    @PostMapping("/consent")
    public String postConsent(HttpServletResponse response, RedirectAttributes redirectAttributes) {
        ResponseCookie cookie = ResponseCookie.from("consentcookie", "true")
                .httpOnly(true)
                .path("/")
                .maxAge(Duration.ofDays(365))
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        redirectAttributes.addFlashAttribute("cookieFeedback", "Tack! Du har nu godkänt användningen av cookies.");
        return "redirect:/";
    }

    @GetMapping("/cookie-policy")
    public String cookiePolicy() {
        return "cookie-policy";
    }

    @GetMapping("/privacy-policy")
    public String integrityPolicy() {
        return "privacy-policy";
    }

    @GetMapping("/ott/sent")
    @ResponseBody
    public String ottSent(){
        return "One Time Token sent!";
    }

}
