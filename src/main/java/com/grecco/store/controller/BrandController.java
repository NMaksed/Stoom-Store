package com.grecco.store.controller;

import com.grecco.store.model.Brand;
import com.grecco.store.model.forms.BrandForm;
import com.grecco.store.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/brand")
public class BrandController {

    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Brand>> getAllBrands() {
        List<Brand> brands = brandService.getAllBrands();
        if (!brands.isEmpty()) {
            return new ResponseEntity<>(brands, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveBrand(@RequestBody BrandForm form) {
        try {
            brandService.saveBrand(form);
            return new ResponseEntity<>("Nova marca salva!", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao criar Marca: " + e.getMessage());
        }
    }

    @PostMapping("/multiple/save")
    public ResponseEntity<String> saveBrands(@RequestBody List<BrandForm> forms) {
        try {
            brandService.saveMultipleBrands(forms);
            return new ResponseEntity<>("Novas marcas salvas!", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao criar Marca: " + e.getMessage());
        }
    }

    @PatchMapping("/{id}/update")
    public ResponseEntity<String> updateBrand(@PathVariable(value = "id") Integer id, @RequestBody BrandForm form) {
        try {
            brandService.updateBrand(id, form);
            return new ResponseEntity<>("Marca atualizada!", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao atualizar Marca: " + e.getMessage());
        }
    }

    @PostMapping("/{id}/deactivate")
    public ResponseEntity<String> deactivateBrand(@PathVariable Integer id) {
        try {
            brandService.deactivateBrand(id);
            return new ResponseEntity<>("Marca desativada!", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao desativar Marca: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteBrand(@PathVariable Integer id) {
        try {
            brandService.deleteBrand(id);
            return new ResponseEntity<>("Marca apagada!", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao apagar Marca: " + e.getMessage());
        }
    }
}
