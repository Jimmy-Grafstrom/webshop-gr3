package se.iths.webshopgr3.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;


    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
            message = "Email is invalid"
    )
    @NotBlank(message = "You must enter an email-address")
    @Column(nullable = false, unique = true, name = "username")
    private String username;

    @NotBlank(message = "You must enter a password")
    @Column(nullable = false, name = "password")
    private String password;

    @Column(nullable = false, name = "consent_given")
    private boolean consent;

    @Column(nullable = false, name = "role")
    private String role;

}
