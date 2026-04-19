package se.iths.webshopgr3.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import se.iths.lw.mailfunctionlibrary.model.Email;
import se.iths.lw.mailfunctionlibrary.service.MessageService;
import se.iths.webshopgr3.model.AppUser;
import se.iths.webshopgr3.repository.AppUserRepository;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class AppUserServiceTestMock {

    @Mock
    private AppUserRepository appUserRepository;

    @Mock
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @InjectMocks
    private AppUserService appUserService;

    @Mock
    private MessageService messageService;

    private AppUser testUser;


    @BeforeEach
    void setUp() {
        testUser = new AppUser();
        testUser.setUsername("test@test.com");
        testUser.setRole(null);
        testUser.setPassword("password123");
        testUser.setConsent(true);
    }

    @Test
    @DisplayName("Assert that save-method is called in repository")
    void saveUserCallsCorrectMethodInRepository() {
        Mockito.when(passwordEncoder.encode(Mockito.any()))
                .thenReturn("encodedPassword");
        appUserService.saveUser(testUser);
        Mockito.verify(appUserRepository).save(testUser);
    }

    @Test
    @DisplayName("Assert that save-method is called when user is saved")
    void findByUsernameRetrievesCorrectUser() {

        Mockito.when(appUserRepository.findByUsername(testUser.getUsername())).thenReturn(Optional.ofNullable(testUser));
        AppUser userReturned = appUserService.findByUsername(testUser.getUsername());
        Assertions.assertEquals(testUser.getId(), userReturned.getId());
    }
    

    @Test
    @DisplayName("Assert that user can be deleted properly")
    void deleteUser() {

        appUserService.deleteUser(testUser.getUsername());
        Mockito.verify(appUserRepository).deleteByUsername(testUser.getUsername());

    }

    @Test
    @DisplayName("Assert that mail is sent")
    void sendAccountInfoToEmail() {
        Mockito.when(appUserRepository.findByUsername(testUser.getUsername())).thenReturn(Optional.of(testUser));
        appUserService.sendAccountInfoToEmail(testUser.getUsername());
        Mockito.verify(messageService).send(Mockito.any(Email.class));

    }

}