package application.data.repository;

import application.data.model.Product;
import application.model.Product.ProductCategory;
import application.viewmodel.common.ProductVM;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("select count(p.id) from product p")
    long getTotalProducts();

    List<Product> getProductByPriceBetween(int a,int b);

    @Query(value = "SELECT new application.viewmodel.common.ProductVM(p.id,p.name,p.image,p.description,p.createdDate,p.updateDate,p.price,p.quantity,c.name,ps.name) FROM product p,category c,product_status ps where p.category=c.id and p.productStatus = ps.id ")
    List<ProductVM> getListPagingProduct(Pageable pageable);

    @Query("Select new application.model.Product.ProductCategory(p.id,p.name,p.image,p.price,p.description) from product p,category c where p.category = c.id and c.id=?1 ")
    List<ProductCategory> getListProductByCategory(int categoryId);


    @Modifying
    @Transactional
    @Query(value = "delete from product where product.product_id = ?1 ",nativeQuery = true)
    void deleteProductId(int id);
}
