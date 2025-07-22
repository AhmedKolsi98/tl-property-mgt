package com.touneslina.loan.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LoanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idLoan;

    @Column(nullable = false)
    private long userId;

    @Column(nullable = false)
    private long propertyId ;

    @Column(nullable = false)
    private String borrowerName ;

    @Column(nullable = false)
    private Date loanDate ;

    private Date expectedReturnDate ;

    private Date actualReturnDate;

    @Enumerated(EnumType.STRING)
    private LoanStatus loanStatus;

    private String comments;


}
