package com.touneslina.loan.service;


import com.touneslina.loan.entity.LoanEntity;
import com.touneslina.loan.repository.LoanRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.touneslina.loan.entity.LoanStatus.CANCELED;

@Service
@AllArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;



    public LoanEntity addLoan(LoanEntity loan){
        return loanRepository.saveAndFlush(loan);
    }

    public List<LoanEntity> findAllLoans (){ return (List<LoanEntity>) loanRepository.findAll(); }

    public LoanEntity updateLoan (LoanEntity updatedLoan, long loanId){
        LoanEntity loan = loanRepository.findById(loanId).orElse(null);
        if (loan!=null){
            loan.setExpectedReturnDate(updatedLoan.getExpectedReturnDate());
            loan.setBorrowerName(updatedLoan.getBorrowerName());
            loan.setActualReturnDate(updatedLoan.getExpectedReturnDate());
            loan.setComments(updatedLoan.getComments());
            loan.setLoanStatus(updatedLoan.getLoanStatus());
            return addLoan(loan);
        }
        return null;
    }

    public Boolean cancelLoan(long idLoan){
        LoanEntity loan = loanRepository.findById(idLoan).orElse(null);
        if (loan!=null){
            loan.setLoanStatus(CANCELED);
            addLoan(loan);
            return true;
        }
        return false;
    }

    public LoanEntity findLoanById (long idLoan){
        return loanRepository.findById(idLoan).orElse(null);
    }

    public List<LoanEntity> findAllLoansByProperty(long idProperty) {
        return loanRepository.findAllByPropertyId(idProperty);
    }
}
