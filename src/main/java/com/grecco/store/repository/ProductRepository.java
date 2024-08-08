package com.grecco.store.repository;

import com.grecco.store.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE Product p SET p.active = false WHERE p.id = :id", nativeQuery = true)
    void deactivateProduct(@Param("id") Integer productId);

    List<Product> findByActiveTrue();

    List<Product> findByBrandIdAndActiveTrue(Integer brandId);

    List<Product> findByCategoryIdAndActiveTrue(Integer categoryId);

    boolean existsByBrandId(Integer brandId);
}
