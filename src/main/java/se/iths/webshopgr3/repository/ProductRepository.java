package se.iths.webshopgr3.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;
import se.iths.webshopgr3.model.Product;

@Repository
public interface ProductRepository extends JpaRepositoryImplementation<Product, Long> {
}
