package se.iths.webshopgr3.service;

import org.springframework.stereotype.Service;
import se.iths.webshopgr3.repository.AppUserRepository;

@Service
public class AppUserService {
    private AppUserRepository userRepository;

    public AppUserService(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
