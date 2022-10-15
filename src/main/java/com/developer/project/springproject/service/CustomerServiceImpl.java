package com.developer.project.springproject.service;

import com.developer.project.springproject.dao.CustomerRepository;
import com.developer.project.springproject.entity.Customer;
import com.developer.project.springproject.security.PasswordHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer registerCustomer(String email, String name, String surname, String password) {
        if (password == null || email == null) {
            return null;
        } else {
            Customer customer = new Customer();
            customer.setEmail(email);
            customer.setName(name);
            customer.setSurname(surname);
            customer.setPassword(PasswordHash.hashPassword(password));
            return customerRepository.save(customer);

        }
    }

    public boolean checkPresence(String email, String password) {
        return customerRepository.findByEmailAndPassword(email, password).isPresent();
    }

    @Override
    public Customer loginCustomer(String email, String password) {
        return customerRepository.findByEmailAndPassword(email, password).orElse(null);
    }
}
