package com.app.model.valid;

import com.app.model.Customer;
import com.app.model.valid.generic.AbstractValidator;

import java.util.Map;

public class CustomerValidatorImpl extends AbstractValidator<Customer> {




    @Override
    public Map<String, String> validate(Customer customer) {
        return errors;
    }
}
