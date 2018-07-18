package application.data.repository;


import application.data.model.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductStatusRepository extends JpaRepository<ProductStatus, Integer> {
}
