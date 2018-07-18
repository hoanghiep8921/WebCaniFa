package application.data.repository;

import application.data.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;

public interface SaleRepository extends JpaRepository<Sale, Integer> {
    @Query(value = "SELECT EXISTS (SELECT s.id FROM sale s where ?1 BETWEEN date(create_date) AND date(end_date))" ,nativeQuery = true)
    int checkSale(Date date);

    @Query(value = "SELECT s.id FROM sale s where ?1 BETWEEN date(create_date) AND date(end_date) and s.status=0 ",nativeQuery = true)
    int findOneSale(Date date);

    @Modifying
    @Query(value = " update sale s set s.status = 1 where s.status = 0  ")
    @Transactional
    void updateSaleAll();
}
