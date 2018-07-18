package application.data.repository;

import application.data.model.ProductHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductHistoryRepository extends JpaRepository<ProductHistory, Integer> {
}