package com.grecco.store.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "brand")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name",nullable = false, length = 100)
    private String name;
    @OneToMany(mappedBy = "brand")
    @JsonManagedReference
    private List<Product> products;
    @Column(name = "active", nullable = false)
    private Boolean active = true;
    @Column(name = "resgistration_date", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "GMT-3")
    private LocalDate registrationDate;
    @ManyToMany
    @JsonBackReference
    @JoinTable(
            name = "brand_category",
            joinColumns = @JoinColumn(name = "brand_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    //Define a data de registro do produto no sistema com a data atual
    @PrePersist
    public void setRegistrationDate() {
        this.registrationDate = LocalDate.now();
    }
}
