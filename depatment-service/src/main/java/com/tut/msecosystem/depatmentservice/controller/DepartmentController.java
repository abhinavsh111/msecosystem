package com.tut.msecosystem.depatmentservice.controller;

import com.tut.msecosystem.depatmentservice.entity.Department;
import com.tut.msecosystem.depatmentservice.service.DepartmentService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departments")
@Slf4j
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/")
    public Department saveDepartment(@RequestBody Department department){
        log.info("Inside saveDepartment");
        return departmentService.saveDepartment(department);
    }

    @GetMapping("/{id}")

    public Department findDepartmentById(@PathVariable("id") Long departmentId){
        log.info("Inside findDepartmentById");
        return departmentService.findDepartmentById(departmentId);
    }


}
