package com.grecco.store.repository;

import com.grecco.store.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE Category c SET c.active = false WHERE c.id = :id", nativeQuery = true)
    void deactivateCategory(@Param("id") Integer categoryId);

    List<Category> findByActiveTrue();

    Optional<Category> findCategoryByIdAndActive(Integer id, Boolean active);
}
