package com.app.model;


import com.app.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Product {

    private String name;
    private Category category;
    private BigDecimal price;
}
