package com.example.companyemploeespring.controller;

import com.example.companyemploeespring.entity.Company;
import com.example.companyemploeespring.entity.Employee;
import com.example.companyemploeespring.repository.CompanyRepository;
import com.example.companyemploeespring.repository.EmployeeRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
public class EmploeeController {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @Value("${company.emploee.spring.images.folder}")
    private String folderPath;

    @GetMapping("/emploee")
    public String users(ModelMap modelMap) {
        List<Employee> all = employeeRepository.findAll();
        modelMap.addAttribute("emploees", all);
        return "emploees";
    }

    @GetMapping("/emploee/add")
    public String addEmploeePage() {
        return "addEmploee";
    }

    @PostMapping("/emploee/add")
    public String addEmployee(@ModelAttribute Employee employee,
                              @RequestParam("emploeeImage") MultipartFile file) throws IOException {

        if (!file.isEmpty() && file.getSize() > 0) {
            String fileName = System.currentTimeMillis() + " " + file.getOriginalFilename();
            File newFile = new File(folderPath + File.separator + fileName);
            file.transferTo(newFile);
            employee.setProfilePic(fileName);
        }
        Company company = companyRepository.getById(employee.getCompany().getId());
        company.setSize(company.getSize() + 1);
        employeeRepository.save(employee);
        companyRepository.save(company);
        return "redirect:/emploees";
    }

    @GetMapping(value = "/emploees/getImage", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@RequestParam("fileName") String fileName) throws IOException {
        InputStream inputStream = new FileInputStream(folderPath + File.separator + fileName);
        return IOUtils.toByteArray(inputStream);
    }

    @GetMapping("/emploees/delete")
    public String delete(@RequestParam("id") int id){
        employeeRepository.deleteById(id);
        return "redirect:/emplees";
    }
}
