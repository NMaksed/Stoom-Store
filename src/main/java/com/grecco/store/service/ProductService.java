package com.grecco.store.service;

import com.grecco.store.model.Brand;
import com.grecco.store.model.Category;
import com.grecco.store.model.Product;
import com.grecco.store.model.forms.ProductForm;
import com.grecco.store.repository.BrandRepository;
import com.grecco.store.repository.CategoryRepository;
import com.grecco.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    ProductService(ProductRepository productRepository,
                   BrandRepository brandRepository,
                   CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findByActiveTrue();
    }

    public List<Product> getProductsByBrand(Integer id) {
        return productRepository.findByBrandIdAndActiveTrue(id);
    }

    public List<Product> getProductsByCategory(Integer id) {
        return productRepository.findByCategoryIdAndActiveTrue(id);
    }

    public void save(ProductForm form) {
        Brand brand = findBrandById(form.getBrand());
        Category category = findCategoryById(form.getCategory());
        Product product = Product.builder().
                description(form.getDescription()).
                price(form.getPrice()).
                active(form.getActive()).
                category(category).
                brand(brand).build();
        productRepository.save(product);
    }

    public void saveMultiple(List<ProductForm> forms) {
        for (ProductForm form : forms) {
            Brand brand = findBrandById(form.getBrand());
            Category category = findCategoryById(form.getCategory());
            Product product = Product.builder().
                    description(form.getDescription()).
                    price(form.getPrice()).
                    active(form.getActive()).
                    category(category).
                    brand(brand).build();
            productRepository.save(product);
        }
    }

    public void updateProduct(ProductForm form, Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto " + id + " não encontrado ou inativo"));

        if (form.getDescription() != null)
            product.setDescription(form.getDescription());
        if (form.getPrice() != null)
            product.setPrice(form.getPrice());
        productRepository.save(product);
    }

    public void delete(Integer id) {
        productRepository.deleteById(id);
    }

    public void deactivate(Integer id) {
        productRepository.deactivateProduct(id);
    }

    public Brand findBrandById(Integer id) {
        Optional<Brand> brand = brandRepository.findBrandByIdAndActive(id, true);
        return brand.orElseThrow(() -> new RuntimeException("Marca" + id + "não encontrada ou inativa!"));
    }

    public Category findCategoryById(Integer id) {
        Optional<Category> category = categoryRepository.findCategoryByIdAndActive(id, true);
        return category.orElse(null);
    }

}
