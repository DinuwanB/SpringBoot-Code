package com.nod.ecom.customer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<String> createCustomer(
            @RequestBody @Valid CustomerRequest customerRequest
    ){
        return ResponseEntity.ok(customerService.createCustomer(customerRequest));
    }

    @PutMapping
    public ResponseEntity<Void> updateCustomer(
            @RequestBody @Valid CustomerRequest customerRequest
    ){
        customerService.updateCustomer(customerRequest);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAll(){
        return ResponseEntity.ok(customerService.findAllCustomers());
    }

    @GetMapping("/exits/{customer_id}")
    public ResponseEntity<Boolean> customerExistsById(
            @PathVariable("customer_id") String customerId
    ){
        return ResponseEntity.ok(customerService.customerExists(customerId));
    }

    @GetMapping("/{customer_id}")
    public ResponseEntity<CustomerResponse> customerFindById(
            @PathVariable("customer_id") String customerId
    ){
        return ResponseEntity.ok(customerService.customerFindById(customerId));
    }

    @DeleteMapping("/{customer_id}")
    public ResponseEntity<Void> delete(
            @PathVariable("customer_id") String customerId
    ){
        customerService.deleteCustomer(customerId);
        return ResponseEntity.accepted().build();
    }

}
