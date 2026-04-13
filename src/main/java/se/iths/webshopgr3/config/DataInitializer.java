package se.iths.webshopgr3.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import se.iths.webshopgr3.model.AppUser;
import se.iths.webshopgr3.repository.AppUserRepository;

@Configuration
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${admin.password:admin}")
    private String adminPassword;

    @Override
    public void run(String... args) throws Exception {
        if (!appUserRepository.existsByUsername("user")) {
            AppUser user = new AppUser();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("user"));
            user.setConsent(true);
            user.setRole("USER");
            appUserRepository.save(user);
        }

        if (!appUserRepository.existsByUsername("admin")) {
            AppUser admin = new AppUser();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setConsent(true);
            admin.setRole("ADMIN");
            appUserRepository.save(admin);
        }
    }
}



