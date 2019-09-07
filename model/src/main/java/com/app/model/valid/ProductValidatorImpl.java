package com.app.model.valid;

import com.app.enums.Category;
import com.app.model.Product;
import com.app.model.valid.generic.AbstractValidator;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;

public class ProductValidatorImpl extends AbstractValidator<Product> {

    private final String PRODUCT_NAME_REGEX = "[A-Z]+";
/*  private String name;
    private Category category;
    private BigDecimal price;*/


    @Override
    public Map<String, String> validate(Product product) {

        if (!isProductNameCorrect(product.getName())) {
            errors.put("Error nr 1", "Name only work with Letters");
        }
        if (!isProductPriceCorrect(product.getPrice())) {
            errors.put("Error nr 2", "Price can't be negative");
        }

        if (!isProductInCorrectCategory(product.getCategory())) {
            errors.put("Error nr 3", "wrong category");
        }
        return errors;
    }

    private boolean isProductNameCorrect(String name) {
        return name.matches(PRODUCT_NAME_REGEX);
    }

    private boolean isProductPriceCorrect(BigDecimal price) {
        return price.compareTo(new BigDecimal(0)) <= 0;
    }

    private boolean isProductInCorrectCategory(Category category) {
        return Arrays.asList(Category.values()).contains(category);
    }

}
