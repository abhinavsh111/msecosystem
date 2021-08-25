package com.tut.msecosystem.depatmentservice.service;

import com.tut.msecosystem.depatmentservice.entity.Department;
import com.tut.msecosystem.depatmentservice.repository.DepartmentRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@Slf4j
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public Department saveDepartment(Department department) {
        log.info("Inside saveDepartment");

        return departmentRepository.save(department);
    }

    @CircuitBreaker(name = "DEPARTMENT-SERVICE", fallbackMethod="dptFallBack")
    public Department findDepartmentById(Long departmentId) {
        return departmentRepository.findById(departmentId).get();
    }

    private Department dptFallBack1(Exception e){
        Department department = new Department();
        department.setDepartmentId(-1000l);
        department.setDepartmentCode("NA");
        department.setDepartmentAddress("NA");
        department.setDepartmentName("NA");
        return department;
    }
}
