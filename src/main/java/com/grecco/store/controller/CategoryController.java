package com.grecco.store.controller;

import com.grecco.store.model.Category;
import com.grecco.store.model.forms.CategoryForm;
import com.grecco.store.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/category")
public class CategoryController {

    public final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> categories = categoryService.getAllCategories();
        if (!categories.isEmpty()) {
            return new ResponseEntity<>(categories, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveCategory(@RequestBody CategoryForm form) {
        try {
            categoryService.save(form);
            return new ResponseEntity<>("Nova categoria salva!", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao criar Categoria: " + e.getMessage());
        }
    }

    @PostMapping("/multiple/save")
    public ResponseEntity<String> saveMultipleCategory(@RequestBody List<CategoryForm> form) {
        try {
            categoryService.saveMultiple(form);
            return new ResponseEntity<>("Novas categorias salvas!", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao criar Categoria: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteCategory(@PathVariable(value = "id") Integer id) {
        try {
            categoryService.delete(id);
            return new ResponseEntity<>("Categoria apagada!", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao apagar Categoria: " + e.getMessage());
        }
    }

    @PostMapping("/{id}/deactivate")
    public ResponseEntity<String> deactivateCategory(@PathVariable(value = "id") Integer id) {
        try {
            categoryService.deactivate(id);
            return new ResponseEntity<>("Categoria desativada!", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao desativar categoria: " + e.getMessage());
        }
    }

    @PatchMapping("/{id}/update")
    public ResponseEntity<String> updateCategory(@PathVariable(value = "id") Integer id, @RequestBody CategoryForm
            form) {
        try {
            categoryService.update(form, id);
            return new ResponseEntity<>("Categoria atualizada!", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao atualizar Categoria: " + e.getMessage());
        }
    }
}
