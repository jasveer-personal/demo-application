package com.example.customerservice.controller;

import com.example.common.model.CustomerVehicleDetails;
import com.example.common.model.customer.Customer;
import com.example.customerservice.repository.CustomerRepository;
import com.example.customerservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/customer")
    public List<Customer> getCustomer() {
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

    @GetMapping(value = "/customer/full/{id}")
    public CustomerVehicleDetails getFullDetails(@PathVariable Integer id) {
        return  customerService.getFullDetails(id);
    }

}
