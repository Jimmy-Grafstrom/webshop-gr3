package se.iths.webshopgr3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se.iths.webshopgr3.model.AppUser;

@Service
@RequiredArgsConstructor
public class AppUserService {
    AppUserService appUserService;

    public void saveUser(AppUser user) {



        appUserService.saveUser(user);
    }

}
