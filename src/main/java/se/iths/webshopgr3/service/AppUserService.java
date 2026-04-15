package se.iths.webshopgr3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.iths.webshopgr3.model.AppUser;
import se.iths.webshopgr3.repository.AppUserRepository;
import se.iths.lw.mailfunctionlibrary.service.MessageService;
import se.iths.lw.mailfunctionlibrary.model.Email;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final MessageService messageService;

    public void saveUser(AppUser user) {

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        if(user.getRole()== null){
            user.setRole("USER");
        }

        appUserRepository.save(user);


    }

    public AppUser findByUsername(String username){
        return appUserRepository.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("Username not found " + username));
    }


    public void deleteUser(String username){

        appUserRepository.deleteByUsername(username);
    }

    public void sendAccountInfoToEmail(String username){
        AppUser appUser = findByUsername(username);

        String userInfo =appUser.toString();

        Email email = new Email();
        email.setRecipient(username);
        email.setSubject("Account info");
        email.setMessage("Here is your account information: " + userInfo +
                "\n Kind regards,\n Webshop-Group 3");
        messageService.send(email);
    }



}
