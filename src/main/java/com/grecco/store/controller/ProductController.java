package com.grecco.store.controller;

import com.grecco.store.model.Product;
import com.grecco.store.model.forms.ProductForm;
import com.grecco.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Product>> getProducts() {
        try {
            List<Product> products = productService.getProducts();
            if (!products.isEmpty()) {
                return new ResponseEntity<>(products, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}/brand")
    public ResponseEntity<List<Product>> getProductsByBrand(@PathVariable(value = "id") Integer id) {
        try {
            List<Product> products = productService.getProductsByBrand(id);
            if (!products.isEmpty()) {
                return new ResponseEntity<>(products, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}/category")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable(value = "id") Integer id) {
        try {
            List<Product> products = productService.getProductsByCategory(id);
            if (!products.isEmpty()) {
                return new ResponseEntity<>(products, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveProduct(@RequestBody ProductForm form) {
        try {
            productService.save(form);
            return new ResponseEntity<>("Novo produto salva!", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao criar Produto: " + e.getMessage());
        }
    }

    @PostMapping("/multiple/save")
    public ResponseEntity<String> saveMultipleProduct(@RequestBody List<ProductForm> form) {
        try {
            productService.saveMultiple(form);
            return new ResponseEntity<>("Novos produtos salvos!", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao salvar Produtos: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Integer id) {
        try {
            productService.delete(id);
            return new ResponseEntity<>("Produto apagado!", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao apagar Produto: " + e.getMessage());
        }
    }

    @PostMapping("/{id}/deactivate")
    public ResponseEntity<String> deactivateProduct(@PathVariable("id") Integer id) {
        try {
            productService.deactivate(id);
            return new ResponseEntity<>("Produto desativado!", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao desativar Produto: " + e.getMessage());
        }
    }

    @PatchMapping("/{id}/update")
    public ResponseEntity<String> updateProduct(@PathVariable(value = "id") Integer id, @RequestBody ProductForm form) {
        try {
            productService.updateProduct(form, id);
            return new ResponseEntity<>("Produto atualizado!", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao atualizar produto: " + e.getMessage());
        }
    }

}
