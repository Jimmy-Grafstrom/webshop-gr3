package se.iths.webshopgr3.config;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.ott.OneTimeToken;
import org.springframework.security.web.authentication.ott.OneTimeTokenGenerationSuccessHandler;
import org.springframework.security.web.authentication.ott.RedirectOneTimeTokenGenerationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import se.iths.lw.mailfunctionlibrary.model.Email;
import se.iths.lw.mailfunctionlibrary.service.MessageService;

import java.io.IOException;

@Component
public class OttSuccessHandler implements OneTimeTokenGenerationSuccessHandler {

    private final MessageService messageService;
    private final RedirectOneTimeTokenGenerationSuccessHandler redirectOneTimeTokenGenerationSuccessHandler;

    public OttSuccessHandler(MessageService messageService,
                             RedirectOneTimeTokenGenerationSuccessHandler redirectOneTimeTokenGenerationSuccessHandler) {
        this.messageService = messageService;
        this.redirectOneTimeTokenGenerationSuccessHandler = redirectOneTimeTokenGenerationSuccessHandler;
    }

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       OneTimeToken oneTimeToken) throws IOException, ServletException {
        String link = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/login/ott")
                .queryParam("token", oneTimeToken.getTokenValue())
                .toUriString();

        Email email = new Email();
        email.setRecipient(request.getParameter("username"));
        email.setMessage(link);
        email.setSubject("One time token link");
        
        // Print the link to console for development testing since no SMTP server is configured
        System.out.println("DEBUG: Login link -> " + link);
        
        messageService.send(email);
        redirectOneTimeTokenGenerationSuccessHandler.handle(request, response, oneTimeToken);

    }
}
