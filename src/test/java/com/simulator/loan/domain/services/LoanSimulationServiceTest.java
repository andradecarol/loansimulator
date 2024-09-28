package com.simulator.loan.domain.services;

import com.simulator.loan.domain.dto.request.LoanSimulatorRequestDTO;
import com.simulator.loan.domain.dto.response.LoanSimulatorResponseDTO;
import com.simulator.loan.mock.LoanSimulatorMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoanSimulationServiceTest {

    @InjectMocks
    private LoanSimulationService service;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetSimulatorSuccessfullyAge25OrLess() {
        //given
        LoanSimulatorRequestDTO request = LoanSimulatorMock.getRequestSimulatorAge25OrLess();
        LoanSimulatorResponseDTO mockResponse = LoanSimulatorMock.getResponseSimulatorAge25OrLess();

        //when
        LoanSimulatorResponseDTO response = service.getSimulator(request);

        //then
        assertEquals(0, mockResponse.getJurosTotalAPagar().compareTo(response.getJurosTotalAPagar()));
        assertEquals(0, mockResponse.getParcelasMensais().compareTo(response.getParcelasMensais()));
        assertEquals(0, mockResponse.getValorTotalAPagar().compareTo(response.getValorTotalAPagar()));
    }

    @Test
    public void testGetSimulatorSuccessfullyAge26To40() {
        // Given
        LoanSimulatorRequestDTO request = LoanSimulatorMock.getRequestSimulatorAge26To40();
        LoanSimulatorResponseDTO mockResponse = LoanSimulatorMock.getResponseSimulatorAge26To40();

        // When
        LoanSimulatorResponseDTO response = service.getSimulator(request);

        // Then
        assertEquals(0, mockResponse.getJurosTotalAPagar().compareTo(response.getJurosTotalAPagar()));
        assertEquals(0, mockResponse.getParcelasMensais().compareTo(response.getParcelasMensais()));
        assertEquals(0, mockResponse.getValorTotalAPagar().compareTo(response.getValorTotalAPagar()));
    }

    @Test
    public void testGetSimulatorSuccessfullyAge41To60() {
        // Given
        LoanSimulatorRequestDTO request = LoanSimulatorMock.getRequestSimulatorAge41To60();
        LoanSimulatorResponseDTO mockResponse = LoanSimulatorMock.getResponseSimulatorAge41To60();

        // When
        LoanSimulatorResponseDTO response = service.getSimulator(request);

        // Then
        assertEquals(0, mockResponse.getJurosTotalAPagar().compareTo(response.getJurosTotalAPagar()));
        assertEquals(0, mockResponse.getParcelasMensais().compareTo(response.getParcelasMensais()));
        assertEquals(0, mockResponse.getValorTotalAPagar().compareTo(response.getValorTotalAPagar()));
    }

    @Test
    public void testGetSimulatorSuccessfullyAgeAbove60() {
        // Given
        LoanSimulatorRequestDTO request = LoanSimulatorMock.getRequestSimulatorAgeAbove60();
        LoanSimulatorResponseDTO mockResponse = LoanSimulatorMock.getResponseSimulatorAgeAbove60();

        // When
        LoanSimulatorResponseDTO response = service.getSimulator(request);

        // Then
        assertEquals(0, mockResponse.getJurosTotalAPagar().compareTo(response.getJurosTotalAPagar()));
        assertEquals(0, mockResponse.getParcelasMensais().compareTo(response.getParcelasMensais()));
        assertEquals(0, mockResponse.getValorTotalAPagar().compareTo(response.getValorTotalAPagar()));
    }

}
