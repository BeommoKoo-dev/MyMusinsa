package org.prgrms.mymusinsa.customer.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.prgrms.mymusinsa.customer.dto.CustomerCreateRequestDTO;
import org.prgrms.mymusinsa.customer.dto.CustomerResponseDTO;
import org.prgrms.mymusinsa.customer.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("api/v1/customers")
@RestController
public class CustomerRestController {

    private final CustomerService customerService;

    @PostMapping("/new")
    public ResponseEntity<CustomerResponseDTO> createCustomer(@Valid @RequestBody CustomerCreateRequestDTO customerCreateRequestDTO) {
        return ResponseEntity.ok(customerService.createCustomer(customerCreateRequestDTO));
    }

}
