package se.iths.webshopgr3.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false, unique = true, name = "username")
    private String username;

    @Column(nullable = false, name = "password")
    private String password;

    @Column(nullable = false, name = "consent_given")
    private boolean consent;

    @Column(nullable = false, name = "role")
    private String role;

    public AppUser() {
    }

    public AppUser(Long id, String username, String password, boolean consent, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.consent = consent;
        this.role = role;
    }
}
