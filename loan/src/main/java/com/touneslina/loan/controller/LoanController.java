package com.touneslina.loan.controller;

import com.touneslina.loan.entity.LoanEntity;
import com.touneslina.loan.service.LoanService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/loans/")
public class LoanController {

    private final LoanService loanService;

    @PostMapping
    public ResponseEntity<LoanEntity> addLoan(@RequestBody LoanEntity loan) {
        LoanEntity createdLoan = loanService.addLoan(loan);
        URI location = URI.create("/api/v1/loans/" + createdLoan.getIdLoan());
        return ResponseEntity.created(location).body(createdLoan);
    }

    @PutMapping("/update/{idLoan}")
    public ResponseEntity<LoanEntity> updateLoan(
            @RequestBody LoanEntity updatedLoan,
            @PathVariable long idLoan){
        LoanEntity loan = loanService.updateLoan(updatedLoan, idLoan);
        if (loan!=null){
            return ResponseEntity.ok(loan);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<LoanEntity>> findALl(){
        List<LoanEntity> loans = (List<LoanEntity>) loanService.findAllLoans();
        return ResponseEntity.ok(loans);
    }

    @PutMapping("/cancel/{idLoan}")
    public ResponseEntity<Void> cancelLoan(@PathVariable long idLoan){
        boolean canceled = loanService.cancelLoan(idLoan);
        if (canceled) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/find/{idLoan}")
    public ResponseEntity<LoanEntity> findById(@PathVariable long idLoan){
        LoanEntity loan = loanService.findLoanById(idLoan);
        if (loan == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(loan);
    }

}
