package se.iths.webshopgr3.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import se.iths.webshopgr3.model.AppUser;
import se.iths.webshopgr3.repository.AppUserRepository;

@ExtendWith(MockitoExtension.class)
class CustomerUserDetailsServiceTestMock {

    @Mock
    private AppUserRepository appUserRepository;

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    private AppUser appUser;

    @BeforeEach
    void setUp() {
        appUser = new AppUser();
        appUser.setRole("USER");
        appUser.setUsername("test@test.com");
        appUser.setPassword("password123");
        appUser.setConsent(true);
    }

    @Test
    @DisplayName("Assert correct details are fetched")
    void loadUserByUsernameWorksProperly() {


    }

    @Test
    @DisplayName("Throws exception since no user is found")
    void loadByUsernameThrowsExceptionSinceUserIsNotFound() {

        Assertions.assertThrows(UsernameNotFoundException.class, () -> {
            customUserDetailsService.loadUserByUsername(appUser.getUsername());
        });

    }

}