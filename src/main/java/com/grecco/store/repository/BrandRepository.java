package com.grecco.store.repository;

import com.grecco.store.model.Brand;
import com.grecco.store.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE Brand b SET b.active = false WHERE b.id = :id", nativeQuery = true)
    void deactivateBrand(@Param("id") Integer brandId);

    List<Brand> findByActiveTrue();

    Optional<Brand> findBrandByIdAndActive(Integer id, Boolean active);

}
