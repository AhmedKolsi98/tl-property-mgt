package com.touneslina.property.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Loans {

    private String borrowerName ;
    private Date loanDate ;
    private Date expectedReturnDate ;
    private Date actualReturnDate;

}
