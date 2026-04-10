package se.iths.webshopgr3;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource (
    properties={
            "spring.mail.username=fake@gmail.com",
        "spring.mail.password=fake"
    })
class WebshopGr3ApplicationTests {

    @Test
    void contextLoads() {
    }

}
