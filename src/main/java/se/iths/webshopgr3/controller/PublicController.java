package se.iths.webshopgr3.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
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

    @GetMapping("/ott/sent")
    @ResponseBody
    public String ottSent(){
        return "One Time Token sent!";
    }

}
