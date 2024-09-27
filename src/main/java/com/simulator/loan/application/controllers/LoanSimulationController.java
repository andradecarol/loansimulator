package com.simulator.loan.application.controllers;

import com.simulator.loan.application.controllers.swagger.LoanSimulationControllerSwagger;
import com.simulator.loan.domain.dto.response.LoanSimulatorResponseDTO;
import com.simulator.loan.domain.services.LoanSimulationService;
import com.simulator.loan.domain.services.interfaces.LoanSimulationServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;

@RequiredArgsConstructor
@Validated
@RestController
public class LoanSimulationController implements LoanSimulationControllerSwagger {

    private final LoanSimulationServiceInterface service;

    @Override
    public ResponseEntity<LoanSimulatorResponseDTO> getSimulator(BigDecimal amount, LocalDate birthDate, Integer months) {
        LoanSimulatorResponseDTO response = service.getSimulator(amount, birthDate, months);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
