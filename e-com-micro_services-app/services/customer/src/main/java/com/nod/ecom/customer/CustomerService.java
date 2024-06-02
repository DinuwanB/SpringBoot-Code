package com.nod.ecom.customer;

import com.nod.ecom.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public String createCustomer(CustomerRequest customerRequest) {
        var customer = customerRepository.save(customerMapper.toCustomer(customerRequest));
        return customer.getId();
    }

    public void updateCustomer(CustomerRequest customerRequest) {
        var customer = customerRepository
                .findById(customerRequest.id())
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("Cannot update customer:: No Customer found with the provided ID:: %s", customerRequest.id())
                ));

        mergeCustomer(customer, customerRequest);
        customerRepository.save(customer);
    }

    public List<CustomerResponse> findAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper:: fromCustomer)
                .collect(Collectors.toList());
    }

    public Boolean customerExists(String customerId) {
        return customerRepository.findById(customerId)
                .isPresent();
    }

    public CustomerResponse customerFindById(String customerId) {
        return customerRepository.findById(customerId)
                .map(customerMapper::fromCustomer)
                .orElseThrow(() ->
                        new CustomerNotFoundException(String
                                .format("Customer not found with the provided ID:: %s", customerId))
                );
    }

    private void mergeCustomer(Customer customer, CustomerRequest customerRequest) {
        if (StringUtils.isNotBlank(customerRequest.firstName())){
            customer.setFirstName(customerRequest.firstName());
        }
        if (StringUtils.isNotBlank(customerRequest.lastName())){
            customer.setFirstName(customerRequest.lastName());
        }
        if (StringUtils.isNotBlank(customerRequest.email())){
            customer.setFirstName(customerRequest.email());
        }
        if (customerRequest.address() != null){
            customer.setAddress(customerRequest.address());
        }
    }


    public void deleteCustomer(String customerId) {
        customerRepository.deleteById(customerId);
    }
}
