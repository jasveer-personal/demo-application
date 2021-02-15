package com.example.customerservice.hystrix;

import com.example.common.model.CustomerVehicleDetails;
import com.example.common.model.customer.Customer;
import com.example.common.model.transport.VehicleTransport;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomerCommand extends HystrixCommand<List<VehicleTransport>> {

    RestTemplate restTemplate;
    String uri;

    public CustomerCommand(RestTemplate restTemplate, String uri) {
        super(HystrixCommandGroupKey.Factory.asKey("default"));
        this.restTemplate = restTemplate;
        this.uri = uri;
    }
    @Override
    protected List<VehicleTransport> run() throws Exception {
        ResponseEntity<VehicleTransport[]> responseEntity = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                null,
                VehicleTransport[].class
        );
        return Arrays.asList(responseEntity.getBody());
    }

    @Override
    protected List<VehicleTransport> getFallback() {
        return new ArrayList<>();
    }
}
