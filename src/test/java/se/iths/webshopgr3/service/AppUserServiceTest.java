package se.iths.webshopgr3.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import se.iths.webshopgr3.model.AppUser;
import se.iths.webshopgr3.repository.AppUserRepository;

@SpringBootTest
class AppUserServiceTest {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private AppUserRepository appUserRepository;

    private AppUser testUser;

    @BeforeEach
    void setUp() {
        testUser = new AppUser();
        testUser.setUsername("username@test.com");
        testUser.setPassword("password");
        testUser.setConsent(true);
    }

    @Test
    @DisplayName("User is saved and can be retrieved from database")
    void saveUser() {
        appUserService.saveUser(testUser);
        AppUser userReturned = appUserService.findByUsername(testUser.getUsername());
        Assertions.assertEquals(testUser.getId(), userReturned.getId());

    }

    @Test
    @DisplayName("User cannot be retrieved from database due to not existing")
    void retrieveUserFails() {
        Assertions.assertThrows(UsernameNotFoundException.class, () -> {
            appUserService.findByUsername(testUser.getUsername());
        });
    }

    @Test
    @DisplayName("User is successfully removed from database")
    void deleteUser() {
        appUserService.saveUser(testUser);
        appUserService.deleteUser(testUser.getUsername());

        Assertions.assertThrows(UsernameNotFoundException.class, () -> {
            appUserService.findByUsername(testUser.getUsername());
        });
    }

    @AfterEach
    void clean() {
        appUserService.deleteUser(testUser.getUsername());
    }

}