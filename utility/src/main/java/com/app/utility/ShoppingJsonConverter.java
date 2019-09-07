package com.app.utility;


import com.app.model.CustomerWithProductsFile;

public class ShoppingJsonConverter extends JsonConverter<CustomerWithProductsFile> {
    public ShoppingJsonConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
