package com.grecco.store.model.forms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductForm {
    /* Classe para receber apenas dados necessários para
       manipular objetos do tipo "Produto"
       assim evitando o uso de entidades e melhorando performace
     */
    private BigDecimal price;
    private String description;
    private Boolean active;
    private Integer category;
    private Integer brand;
}
