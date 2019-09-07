package com.app.service;



import com.app.excetption.MyUncheckedException;
import com.app.model.CustomerWithProducts;
import com.app.model.CustomerWithProductsFile;
import com.app.model.Product;
import com.app.utility.CustomerGenerator;
import com.app.utility.ProductGenerator;
import com.app.utility.ShoppingJsonConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataManager {

    private final Scanner sc = new Scanner(System.in);

    public DataManager() {
        saveDataToFiles();
    }

    public Integer getInt(String message) {
        System.out.println(message);

        String line = sc.nextLine();
        if (line == null || !line.matches("\\d+")) {
            throw new MyUncheckedException("WRONG DATA TRY AGIAN");
        }

        return Integer.parseInt(line);
    }

    public String getLine(String message) {
        System.out.println(message);
        return sc.nextLine();
    }


    public void close() {
        if (sc != null) {
            sc.close();
        }
    }

    private List<CustomerWithProducts> customerWithProductListGenerator() {
        List<CustomerWithProducts> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            CustomerWithProducts cp = new CustomerWithProducts(CustomerGenerator.customerGenerator(), productsGenerator());
            list.add(cp);
        }
        return list;
    }

    private List<Product> productsGenerator() {
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            products.add(ProductGenerator.productGeneratorAll());
        }
        return products;
    }

    /**
     * This method save data to Json Files
     * creat List<CustomerWithProducts> and CustomerWithProductsFile
     * and ShoppingJsonConverter to send Data to Json File;
     */

    private void saveDataToFiles() {
//App03jsonFile01
        for (int i = 1; i < 4; i++) {
            List<CustomerWithProducts> customerWithProductsList = customerWithProductListGenerator();
            String fileName = "App03jsonFile0" + i + ".json";
            CustomerWithProductsFile cpf = new CustomerWithProductsFile(customerWithProductsList);
            ShoppingJsonConverter shoppingJsonConverter = new ShoppingJsonConverter(fileName);
            shoppingJsonConverter.toJson(cpf);
        }
    }

}
