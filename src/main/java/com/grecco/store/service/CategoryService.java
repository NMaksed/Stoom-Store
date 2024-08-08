package com.grecco.store.service;

import com.grecco.store.model.Category;
import com.grecco.store.model.forms.CategoryForm;
import com.grecco.store.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    public final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findByActiveTrue();
    }

    public void save(CategoryForm form) {
        Category category = Category.builder()
                .description(form.getDescription())
                .active(form.isActive()).build();
        categoryRepository.save(category);
    }

    public void saveMultiple(List<CategoryForm> forms) {
        for (CategoryForm form : forms) {
            Category category = Category.builder()
                    .description(form.getDescription())
                    .active(form.isActive()).build();
            categoryRepository.save(category);
        }
    }

    public void delete(Integer id) {
        categoryRepository.deleteById(id);
    }

    public void deactivate(Integer id) {
        categoryRepository.deactivateCategory(id);
    }

    public void update(CategoryForm form, Integer id) {
        Category category = categoryRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Categoria" + id + " não encontrada ou inativada"));

        if (form.getDescription() != null)
            category.setDescription(form.getDescription());
        category.setActive(form.isActive());
        categoryRepository.save(category);
    }
}
