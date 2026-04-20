package se.iths.webshopgr3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import se.iths.lw.mailfunctionlibrary.messaging.Messenger;
import se.iths.lw.mailfunctionlibrary.service.MessageService;

import java.util.Map;

@Configuration
public class TestConfig {

    @Bean
    @Primary
    public MessageService fakeMessageService() {
        Messenger messenger =
                message -> System.out.println("Sending: " + message);
        return new MessageService(Map.of("email", messenger));
    }

}
