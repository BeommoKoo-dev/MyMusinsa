package org.prgrms.mymusinsa.customer.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.prgrms.mymusinsa.customer.domain.Customer;
import org.prgrms.mymusinsa.customer.dto.CustomerCreateRequestDTO;
import org.prgrms.mymusinsa.customer.dto.CustomerLoginDTO;
import org.prgrms.mymusinsa.customer.dto.CustomerResponseDTO;
import org.prgrms.mymusinsa.customer.repository.CustomerJdbcRepository;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CustomerService {

    private final CustomerJdbcRepository customerJdbcRepository;

    @Transactional
    public CustomerResponseDTO createCustomer(CustomerCreateRequestDTO customerCreateRequestDTO) {
        Customer customer = customerCreateRequestDTO.toCustomer();
        customerJdbcRepository.insert(customer);
        return customer.toCustomerResponseDTO();
    }

    public void login(CustomerLoginDTO customerLoginDTO) {
        Customer customer = customerLoginDTO.toCustomer();
        customerJdbcRepository.findByEmailAndPassword(customer);
    }

}
