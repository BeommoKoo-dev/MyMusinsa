package org.prgrms.mymusinsa.customer.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.prgrms.mymusinsa.customer.domain.Customer;
import org.prgrms.mymusinsa.customer.domain.Email;
import org.prgrms.mymusinsa.customer.dto.CustomerCreateRequestDTO;
import org.prgrms.mymusinsa.customer.dto.CustomerLoginRequestDTO;
import org.prgrms.mymusinsa.customer.dto.CustomerResponseDTO;
import org.prgrms.mymusinsa.customer.repository.CustomerJdbcRepository;
import org.prgrms.mymusinsa.global.exception.ErrorCode;
import org.prgrms.mymusinsa.global.exception.GlobalCustomException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomerService {

    private final CustomerJdbcRepository customerJdbcRepository;

    @Transactional
    public CustomerResponseDTO createCustomer(CustomerCreateRequestDTO customerCreateRequestDTO) {
        Customer customer = customerCreateRequestDTO.toCustomer();
        return customerJdbcRepository.insert(customer).toCustomerResponseDTO();
    }

    public void login(CustomerLoginRequestDTO customerLoginRequestDTO) {
        Email email = new Email(customerLoginRequestDTO.email());
        Optional<Customer> customer = customerJdbcRepository.checkExistenceByEmailAndPassWord(
            email,
            customerLoginRequestDTO.password()
        );
        if(customer.isEmpty()) {
            throw new GlobalCustomException(ErrorCode.LOGIN_ERROR);
        }
    }

}
