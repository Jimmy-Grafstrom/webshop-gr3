package se.iths.webshopgr3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PublicController {

    @GetMapping
    public String start() {
        return "start";
    }

    @GetMapping("/cookie-policy")
    public String cookiePolicy() {
        return "cookie-policy";
    }

    @GetMapping("/privacy-policy")
    public String integrityPolicy() {
        return "privacy-policy";
    }

}
