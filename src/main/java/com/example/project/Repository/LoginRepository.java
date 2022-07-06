package com.example.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project.model.login;



public interface LoginRepository extends JpaRepository<login , Long> {
    
}
