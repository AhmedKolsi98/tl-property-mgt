package com.touneslina.property.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="loan-service", url="${application.config.loans-url}")
public interface LoanClient {

    @GetMapping("/property/{idProperty}")
    List<Loans> findAllLoansByProperty(@PathVariable long idProperty);

}
