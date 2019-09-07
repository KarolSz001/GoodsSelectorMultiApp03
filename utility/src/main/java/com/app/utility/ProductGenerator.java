package com.app.utility;

import com.app.enums.Category;
import com.app.excetption.MyUncheckedException;
import com.app.model.Product;
import com.app.model.valid.ProductValidatorImpl;


import java.math.BigDecimal;
import java.util.Random;

public class ProductGenerator {


    public static Product productFoodGenerator() {
        String[] nameArr = {"Banan", "Choco", "Apple", "IceCream"};
        String name = selectName(nameArr);
        Category category = Category.FOOD;
        int cash = new Random().nextInt(300 - 100 + 1) + 100;
        BigDecimal price = new BigDecimal(cash);
        return new Product(name, category, price);
    }

    public static Product productBookGenerator() {
        String[] nameArr = {"Alladyn", "Game of Throne", "Boogeman", "Last Mochcanine"};
        String name = selectName(nameArr);
        Category category = Category.BOOK;
        int cash = new Random().nextInt(1000 - 300 + 1) + 300;
        BigDecimal price = new BigDecimal(cash);
        return new Product(name, category, price);
    }

    public static Product productElectronicGenerator() {
        String[] nameArr = {"GameBoy", "Radio", "Laptop", "Phone"};
        String name = selectName(nameArr);
        Category category = Category.ELECTRONIC;
        int cash = new Random().nextInt(30000 - 10000 + 1) + 10000;
        BigDecimal price = new BigDecimal(cash);
        return new Product(name, category, price);
    }

    private static String selectName(String arr[]) {
        int size = arr.length;
        int index = new Random().nextInt(size);
        return arr[index];
    }

    public static Product productGeneratorAll() {
        Product result = new Product();
        ProductValidatorImpl productValidator = new ProductValidatorImpl();
        int number = new Random().nextInt(3 - 1 + 1) + 1;
        if (number == 1) result = productBookGenerator();
        else if (number == 2) result = productElectronicGenerator();
        else if (number == 3) result = productFoodGenerator();
        productValidator.validate(result);
        if (productValidator.hasErrors()) {
            System.out.println("valid errors list ---->>>>>>> ");
            productValidator.getErrors().forEach((k, v) -> System.out.println(k + "::::::::::::" + v));
            throw new MyUncheckedException("product is not valid");
        }
        return result;
    }
}
