package se.iths.webshopgr3;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages =
        {"se.iths.lw.mailfunctionlibrary",
            "se.iths.webshopgr3"})
public class WebshopGr3Application {

    public static void main(String[] args) {
        SpringApplication.run(WebshopGr3Application.class, args);
    }


}
