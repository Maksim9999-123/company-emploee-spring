package com.example.companyemploeespring.controller;

import com.example.companyemploeespring.entity.Company;
import com.example.companyemploeespring.entity.Employee;
import com.example.companyemploeespring.repository.CompanyRepository;
import com.example.companyemploeespring.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/companie")
    public String addCompanyPage(ModelMap modelMap) {
        List<Company> companies = companyRepository.findAll();
        modelMap.addAttribute("companies", companies);
        return "companies";
    }

    @PostMapping("/company/add")
    public String addCompany(@ModelAttribute Company company) {
        companyRepository.save(company);
        return "redirect:/companies";
    }

    @GetMapping("/company/add")
    public String companiesPage(ModelMap modelMap) {
        List<Company> companies = companyRepository.findAll();
        List<Employee> employees = employeeRepository.findAll();
        modelMap.addAttribute("companies", companies);
        modelMap.addAttribute("employees", employees);
        return "companies";
    }

    @GetMapping("/company/delete/{id}")
    public String deleteCompany(@PathVariable("id") int id) {
        employeeRepository.deleteAllByCompanyId(id);
        companyRepository.deleteById(id);
        return "redirect:/companies";
    }
}
