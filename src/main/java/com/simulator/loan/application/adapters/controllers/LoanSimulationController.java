package com.simulator.loan.application.adapters.controllers;

import com.simulator.loan.application.adapters.controllers.swagger.LoanSimulationControllerSwagger;
import com.simulator.loan.domain.dto.request.LoanSimulatorRequestDTO;
import com.simulator.loan.domain.dto.response.LoanSimulatorResponseDTO;
import com.simulator.loan.ports.LoanSimulationServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Validated
@RestController
public class LoanSimulationController implements LoanSimulationControllerSwagger {

    private final LoanSimulationServicePort service;

    @Override
    public ResponseEntity<LoanSimulatorResponseDTO> getSimulator(LoanSimulatorRequestDTO request) {
        LoanSimulatorResponseDTO response = service.getSimulator(request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }
}
