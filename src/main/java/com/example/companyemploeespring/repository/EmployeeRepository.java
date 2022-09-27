package com.example.companyemploeespring.repository;

import com.example.companyemploeespring.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    void deleteAllByCompanyId(int id);
}
