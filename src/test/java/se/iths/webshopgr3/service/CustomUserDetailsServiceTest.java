package se.iths.webshopgr3.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import se.iths.webshopgr3.model.AppUser;

@SpringBootTest
class CustomUserDetailsServiceTest {

    private AppUser testUser;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @BeforeEach
    void setUp() {
        testUser = new AppUser();
        testUser.setConsent(true);
        testUser.setPassword("password");
        testUser.setUsername("test@test.com");
    }

    @Test
    @DisplayName("User is loaded correctly")
    void loadUserByUsernameSucceeds() {
        appUserService.saveUser(testUser);
        UserDetails userReturned = customUserDetailsService.loadUserByUsername(testUser.getUsername());

        Assertions.assertEquals(testUser.getUsername(), userReturned.getUsername());


    }

    @Test
    @DisplayName("User does not exists. Exception is thrown.")
    void loadUserByUserNameFails() {
        Assertions.assertThrows(UsernameNotFoundException.class, () -> {
            customUserDetailsService.loadUserByUsername(testUser.getUsername());
        });

    }

    @AfterEach
    void clean() {
        appUserService.deleteUser(testUser.getUsername());
    }
}