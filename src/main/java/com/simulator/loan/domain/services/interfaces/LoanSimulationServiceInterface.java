package com.simulator.loan.domain.services.interfaces;

import com.simulator.loan.domain.dto.request.LoanSimulatorRequestDTO;
import com.simulator.loan.domain.dto.response.LoanSimulatorResponseDTO;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface LoanSimulationServiceInterface {

    LoanSimulatorResponseDTO getSimulator(BigDecimal amount, LocalDate birthDate, Integer months);

}
