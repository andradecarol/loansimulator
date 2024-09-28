package com.simulator.loan.mock;

import com.simulator.loan.domain.dto.request.LoanSimulatorRequestDTO;
import com.simulator.loan.domain.dto.response.LoanSimulatorResponseDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LoanSimulatorMock {

    public static LoanSimulatorResponseDTO getResponseSimulatorAge25OrLess(){
        return LoanSimulatorResponseDTO.builder()
                .valorTotalAPagar(BigDecimal.valueOf(1024.40))
                .parcelasMensais(BigDecimal.valueOf(102.44))
                .jurosTotalAPagar(BigDecimal.valueOf(24.40))
                .build();
    }

    public static LoanSimulatorResponseDTO getResponseSimulatorAge26To40(){
        return LoanSimulatorResponseDTO.builder()
                .valorTotalAPagar(BigDecimal.valueOf(1012.10))
                .parcelasMensais(BigDecimal.valueOf(101.21))
                .jurosTotalAPagar(BigDecimal.valueOf(12.10))
                .build();
    }

    public static LoanSimulatorResponseDTO getResponseSimulatorAge41To60(){
        return LoanSimulatorResponseDTO.builder()
                .valorTotalAPagar(BigDecimal.valueOf(1011.90))
                .parcelasMensais(BigDecimal.valueOf(101.19))
                .jurosTotalAPagar(BigDecimal.valueOf(11.90))
                .build();
    }

    public static LoanSimulatorResponseDTO getResponseSimulatorAgeAbove60(){
        return LoanSimulatorResponseDTO.builder()
                .valorTotalAPagar(BigDecimal.valueOf(1018.50))
                .parcelasMensais(BigDecimal.valueOf(101.85))
                .jurosTotalAPagar(BigDecimal.valueOf(18.50))
                .build();
    }

    public static LoanSimulatorRequestDTO getRequestSimulatorAge25OrLess(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LoanSimulatorRequestDTO.builder()
                .amount(BigDecimal.valueOf(1000))
                .birthDate(LocalDate.parse("16/04/1999", formatter))
                .months(10)
                .build();
    }

    public static LoanSimulatorRequestDTO getRequestSimulatorAge26To40(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LoanSimulatorRequestDTO.builder()
                .amount(BigDecimal.valueOf(1000))
                .birthDate(LocalDate.parse("16/04/1991", formatter))
                .months(10)
                .build();
    }

    public static LoanSimulatorRequestDTO getRequestSimulatorAge41To60(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LoanSimulatorRequestDTO.builder()
                .amount(BigDecimal.valueOf(1000))
                .birthDate(LocalDate.parse("16/04/1966", formatter))
                .months(10)
                .build();
    }

    public static LoanSimulatorRequestDTO getRequestSimulatorAgeAbove60(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LoanSimulatorRequestDTO.builder()
                .amount(BigDecimal.valueOf(1000))
                .birthDate(LocalDate.parse("16/04/1955", formatter))
                .months(10)
                .build();
    }
}
