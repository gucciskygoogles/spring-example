package com.developer.project.springproject.dao;

import com.developer.project.springproject.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Optional<Customer> findByEmailAndPassword(String email, String password);
}
