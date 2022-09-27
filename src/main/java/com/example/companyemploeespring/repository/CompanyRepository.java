package com.example.companyemploeespring.repository;


import com.example.companyemploeespring.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
