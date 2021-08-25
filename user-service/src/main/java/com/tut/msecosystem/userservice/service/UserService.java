package com.tut.msecosystem.userservice.service;

import com.tut.msecosystem.userservice.entity.User;
import com.tut.msecosystem.userservice.pojo.Department;
import com.tut.msecosystem.userservice.pojo.Response;
import com.tut.msecosystem.userservice.repository.UserRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public User saveUser(User user) {
        log.info("inside saveUser");
        return userRepository.save(user);
    }

    @CircuitBreaker(name = "USER-SERVICE", fallbackMethod="departmentFallBack")
    public Response getUserWithDepartment(Long userId) {
        log.info("inside getUserWithDepartment");
       Response response = new Response();
       User user = userRepository.findByUserId(userId);
        response.setUser(user);
        Department department = restTemplate.getForObject("http://DEPARTMENT-SERVICE:9001/departments/" + user.getDepartmentId(), Department.class);
        response.setDepartment(department);
        return response;
    }

    private Response departmentFallBack(Exception e){
        Response response = new Response();
        return response;
    }
}
