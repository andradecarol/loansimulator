package com.simulator.loan.domain.services;

import com.simulator.loan.config.application.MessageConfig;
import com.simulator.loan.domain.dto.request.LoanSimulatorRequestDTO;
import com.simulator.loan.domain.dto.response.LoanSimulatorResponseDTO;
import com.simulator.loan.domain.exceptions.UnprocessableEntityException;
import com.simulator.loan.mock.LoanSimulatorMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.simulator.loan.domain.exceptions.MessageErrorCodeConstants.INVALID_AGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoanSimulationServiceTest {

    @Mock
    private MessageConfig messageConfig;

    @InjectMocks
    private LoanSimulationService service;

    @Test
    void shouldGetSimulatorSuccessfullyAge25OrLess() {
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
    void shouldGetSimulatorSuccessfullyAge26To40() {
        // given
        LoanSimulatorRequestDTO request = LoanSimulatorMock.getRequestSimulatorAge26To40();
        LoanSimulatorResponseDTO mockResponse = LoanSimulatorMock.getResponseSimulatorAge26To40();

        // when
        LoanSimulatorResponseDTO response = service.getSimulator(request);

        // then
        assertEquals(0, mockResponse.getJurosTotalAPagar().compareTo(response.getJurosTotalAPagar()));
        assertEquals(0, mockResponse.getParcelasMensais().compareTo(response.getParcelasMensais()));
        assertEquals(0, mockResponse.getValorTotalAPagar().compareTo(response.getValorTotalAPagar()));
    }

    @Test
    void shouldGetSimulatorSuccessfullyAge41To60() {
        // given
        LoanSimulatorRequestDTO request = LoanSimulatorMock.getRequestSimulatorAge41To60();
        LoanSimulatorResponseDTO mockResponse = LoanSimulatorMock.getResponseSimulatorAge41To60();

        // when
        LoanSimulatorResponseDTO response = service.getSimulator(request);

        // then
        assertEquals(0, mockResponse.getJurosTotalAPagar().compareTo(response.getJurosTotalAPagar()));
        assertEquals(0, mockResponse.getParcelasMensais().compareTo(response.getParcelasMensais()));
        assertEquals(0, mockResponse.getValorTotalAPagar().compareTo(response.getValorTotalAPagar()));
    }

    @Test
    void shouldGetSimulatorSuccessfullyAgeAbove60() {
        // given
        LoanSimulatorRequestDTO request = LoanSimulatorMock.getRequestSimulatorAgeAbove60();
        LoanSimulatorResponseDTO mockResponse = LoanSimulatorMock.getResponseSimulatorAgeAbove60();

        // when
        LoanSimulatorResponseDTO response = service.getSimulator(request);

        // then
        assertEquals(0, mockResponse.getJurosTotalAPagar().compareTo(response.getJurosTotalAPagar()));
        assertEquals(0, mockResponse.getParcelasMensais().compareTo(response.getParcelasMensais()));
        assertEquals(0, mockResponse.getValorTotalAPagar().compareTo(response.getValorTotalAPagar()));
    }

    @Test
    public void testGetSimulatorThrowsUnprocessableEntityException() {
        LoanSimulatorRequestDTO request = LoanSimulatorMock.getRequestSimulatorInvalidAge();

        when(messageConfig.getMessage(INVALID_AGE)).thenReturn("Idade inválida para empréstimo.");

        UnprocessableEntityException thrown = assertThrows(UnprocessableEntityException.class, () -> {
            service.getSimulator(request); // Chama o método real para causar a exceção
        });

        assertEquals("Idade inválida para empréstimo.", thrown.getMessage());
    }

}
