package com.developer.project.springproject.service;

import com.developer.project.springproject.entity.Customer;

public interface CustomerService {

    public Customer registerCustomer(String email, String name, String surname, String password);

    public Customer loginCustomer(String email, String password);

    public boolean checkPresence(String email, String password);


}
