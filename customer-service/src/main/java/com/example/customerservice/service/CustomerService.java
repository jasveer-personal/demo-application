package com.example.customerservice.service;

import com.example.common.model.CustomerVehicleDetails;
import com.example.common.model.customer.Customer;
import com.example.common.model.transport.CustomerTransport;
import com.example.common.model.transport.VehicleTransport;
import com.example.common.model.vehicle.Vehicle;
import com.example.customerservice.hystrix.CommonHystrixCommand;
import com.example.customerservice.hystrix.CustomerCommand;
import com.example.customerservice.repository.CustomerRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
public class CustomerService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CustomerRepository customerRepository;

    @HystrixCommand(fallbackMethod = "getDefaultCustDetails", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000") })
    public CustomerVehicleDetails getFullDetails(Integer id) {
        CustomerVehicleDetails customerVehicleDetails = new CustomerVehicleDetails();
        Customer customer = customerRepository.findById(id).orElse(new Customer());
        CustomerTransport customerTransport = new CustomerTransport();
        customerTransport.setId(customer.getId());
        customerTransport.setName(customer.getName());
        customerTransport.setEmail(customer.getEmailId());
        customerVehicleDetails.setCustomer(customerTransport);
        String url = "http://vehicle/custvehicle/" + id;
        ResponseEntity<VehicleTransport[]> responseEntity =
                restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        null,
                        VehicleTransport[].class
                );
        customerTransport.setVehicleTransport(Arrays.asList(responseEntity.getBody()));

        return customerVehicleDetails;
    }

    public CustomerVehicleDetails  getDefaultCustDetails(Integer id) {
        return new CustomerVehicleDetails();
    }


    public CustomerVehicleDetails getFullDetailsUsingCircuitBreakerClass(Integer id) {

        CustomerVehicleDetails customerVehicleDetails = new CustomerVehicleDetails();
        Customer customer = customerRepository.findById(id).orElse(new Customer());
        CustomerTransport customerTransport = new CustomerTransport();
        customerTransport.setId(customer.getId());
        customerTransport.setName(customer.getName());
        customerTransport.setEmail(customer.getEmailId());
        customerVehicleDetails.setCustomer(customerTransport);
        String url = "http://vehicle/custvehicle/" + id;

        CustomerCommand customerCommand = new CustomerCommand(restTemplate, url);
        customerTransport.setVehicleTransport(customerCommand.execute());

        return customerVehicleDetails;
    }

    public CustomerVehicleDetails getFullDetailsUsingCommonCircuitBreakerClass(Integer id) {
        CustomerVehicleDetails customerVehicleDetails = new CustomerVehicleDetails();
        Customer customer = customerRepository.findById(id).orElse(new Customer());
        CustomerTransport customerTransport = new CustomerTransport();
        customerTransport.setId(customer.getId());
        customerTransport.setName(customer.getName());
        customerTransport.setEmail(customer.getEmailId());
        customerVehicleDetails.setCustomer(customerTransport);
        String url = "http://vehicle/custvehicle/" + id;

        CommonHystrixCommand<VehicleTransport[]> customerCommonCommand = new CommonHystrixCommand<VehicleTransport[]>("default",
                () ->
                {
                    return restTemplate.exchange(
                            url,
                            HttpMethod.GET,
                            null,
                            VehicleTransport[].class
                    ).getBody();
                }, () -> {
            VehicleTransport[] vehicleTransports = {};
            return vehicleTransports;
        });

        Future<VehicleTransport[]> futureData = customerCommonCommand.queue();
        try {
            VehicleTransport[] vehicleTransport = futureData.get();
            customerTransport.setVehicleTransport(Arrays.asList(vehicleTransport));
        } catch (Exception ex) {
        System.out.print("---------------------------------------there was an error in future get----------------");
        }

        return customerVehicleDetails;
    }
}
