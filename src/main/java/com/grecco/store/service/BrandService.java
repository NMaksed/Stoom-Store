package com.grecco.store.service;

import com.grecco.store.model.Brand;
import com.grecco.store.model.forms.BrandForm;
import com.grecco.store.repository.BrandRepository;
import com.grecco.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BrandService {

    public final BrandRepository brandRepository;
    public final ProductRepository productRepository;

    @Autowired
    public BrandService(BrandRepository brandRepository,
                        ProductRepository productRepository) {
        this.brandRepository = brandRepository;
        this.productRepository = productRepository;
    }

    public List<Brand> getAllBrands() {
        return brandRepository.findByActiveTrue();
    }

    public void saveBrand(BrandForm form) {
        Brand brand = Brand.builder().
                name(form.getName()).
                active(form.active).build();
        brandRepository.save(brand);
    }

    public void saveMultipleBrands(List<BrandForm> forms) {
        for (BrandForm form : forms) {
            Brand brand = Brand.builder().
                    name(form.getName()).
                    active(form.active).build();
            brandRepository.save(brand);
        }
    }

    public void updateBrand(Integer id, BrandForm form) {
        Brand brand = brandRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Marca" + id + " não encontrada ou inativada"));
        if (form.getName() != null)
            brand.setName(form.getName());
        brand.setActive(form.active);
        brandRepository.save(brand);
    }

    public void deleteBrand(Integer id) throws Exception {
        if (productRepository.existsByBrandId(id))
            throw new Exception("Impossivel apagar Marca, pois possui produtos!");
        brandRepository.deleteById(id);
    }

    public void deactivateBrand(Integer id) {
        brandRepository.deactivateBrand(id);
    }
}
