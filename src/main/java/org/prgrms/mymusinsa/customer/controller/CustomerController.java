package org.prgrms.mymusinsa.customer.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.prgrms.mymusinsa.customer.dto.CustomerCreateRequestDTO;
import org.prgrms.mymusinsa.customer.dto.CustomerLoginRequestDTO;
import org.prgrms.mymusinsa.customer.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("customers")
@Controller
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/login")
    public String viewLoginPage() {
        return "login";
    }

    @GetMapping("/signup")
    public String viewSignUpPage() {
        return "signup";
    }

    @PostMapping("/login")
    public String login(@Valid CustomerLoginRequestDTO customerLoginDTO) {
        customerService.login(customerLoginDTO);
        return "redirect:/products";
    }

    @PostMapping("/signup")
    public String signUp(@Valid CustomerCreateRequestDTO customerCreateRequestDTO){
        customerService.createCustomer(customerCreateRequestDTO);
        return "redirect:/customers/login";
    }

}
