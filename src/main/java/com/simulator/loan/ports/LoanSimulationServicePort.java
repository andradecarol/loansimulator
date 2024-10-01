package com.simulator.loan.ports;

import com.simulator.loan.domain.dto.request.LoanSimulatorRequestDTO;
import com.simulator.loan.domain.dto.response.LoanSimulatorResponseDTO;

public interface LoanSimulationServicePort {

    LoanSimulatorResponseDTO getSimulator(LoanSimulatorRequestDTO request);

}
