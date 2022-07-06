package com.example.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project.model.Customer;

public interface CustomerRepository  extends JpaRepository<Customer , Long>{
    
}
