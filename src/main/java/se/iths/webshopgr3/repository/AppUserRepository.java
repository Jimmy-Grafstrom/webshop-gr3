package se.iths.webshopgr3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.iths.webshopgr3.model.AppUser;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
}
