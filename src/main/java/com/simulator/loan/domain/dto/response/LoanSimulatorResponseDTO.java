package com.simulator.loan.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LoanSimulatorResponseDTO {

    private BigDecimal valorTotalAPagar;
    private BigDecimal parcelasMensais;
    private BigDecimal jurosTotalAPagar;

}
