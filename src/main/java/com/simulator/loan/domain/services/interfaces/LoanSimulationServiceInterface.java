package com.simulator.loan.domain.services.interfaces;

import com.simulator.loan.domain.dto.request.LoanSimulatorRequestDTO;
import com.simulator.loan.domain.dto.response.LoanSimulatorResponseDTO;

public interface LoanSimulationServiceInterface {

    LoanSimulatorResponseDTO getSimulator(LoanSimulatorRequestDTO request);

}
