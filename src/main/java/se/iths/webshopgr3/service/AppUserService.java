package se.iths.webshopgr3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.iths.webshopgr3.model.AppUser;
import se.iths.webshopgr3.repository.AppUserRepository;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;



    public void saveUser(AppUser user) {

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        if(user.getRole()== null){
            user.setRole("USER");
        }

        appUserRepository.save(user);
    }

    public void deleteUser(String username){

        appUserRepository.deleteByUsername(username);
    }



}
