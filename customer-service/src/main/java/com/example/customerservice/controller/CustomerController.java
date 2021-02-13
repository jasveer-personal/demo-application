package com.example.customerservice.controller;

import com.example.common.model.Customer;
import com.example.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class CustomerController {

    public CustomerController() {
        System.out.println("--------------------------------------------------------------------------this is customer controller");
    }
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/customer")
    public List<Customer> getCustomer() {

        Integer vehicleId = 9;
        restTemplate.getForEntity("http://customer/customer/2", Object.class);
        return customerRepository.findAll();
    }

    @GetMapping("/customer/{id}")
    public Customer getCustomer(@PathVariable Integer id) {

        return customerRepository.findById(id).orElse(new Customer());
    }

    @PostMapping(value = "/customer", consumes = "application/json")
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }
}
