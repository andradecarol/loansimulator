package com.simulator.loan.domain.services;

import com.simulator.loan.config.application.MessageConfig;
import com.simulator.loan.domain.dto.request.LoanSimulatorRequestDTO;
import com.simulator.loan.domain.dto.response.LoanSimulatorResponseDTO;
import com.simulator.loan.domain.exceptions.InternalServerErrorException;
import com.simulator.loan.domain.exceptions.MessageErrorCodeConstants;
import com.simulator.loan.domain.exceptions.UnprocessableEntityException;
import com.simulator.loan.mock.LoanSimulatorMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import static com.simulator.loan.domain.exceptions.MessageErrorCodeConstants.INTERNAL_SERVER_ERROR;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LoanSimulationServiceTest {

    @InjectMocks
    private LoanSimulationService service;

    @Mock
    private MessageConfig messageConfig;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldGetSimulatorSuccessfullyAge25OrLess() {
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
    public void shouldGetSimulatorSuccessfullyAge26To40() {
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
    public void shouldGetSimulatorSuccessfullyAge41To60() {
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
    public void shouldGetSimulatorSuccessfullyAgeAbove60() {
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

//    @Test
//    public void testGetSimulatorThrowsUnprocessableEntityException() {
//        // Given: Um request que irá causar um erro de regra de negócio
//        LoanSimulatorRequestDTO request = LoanSimulatorMock.getRequestSimulatorAge25OrLess();
//
//        // Aqui, você deve simular a regra que causaria a exceção, como valores incorretos
//        doThrow(new UnprocessableEntityException("422.000", "Erro de negócio."))
//                .when(messageConfig).getMessage(MessageErrorCodeConstants.BUSINESS_ERROR); // Mockando o comportamento de messageConfig
//
//        // When / Then: Validando se a exceção é lançada corretamente
//        UnprocessableEntityException thrown = assertThrows(UnprocessableEntityException.class, () -> {
//            service.getSimulator(request); // Chama o método real para causar a exceção
//        });
//
//        // Validação da mensagem da exceção
//        assertEquals("Erro de negócio", thrown.getMessage());
//    }
//
//    @Test
//    public void testGetSimulatorThrowsInternalServerErrorException() {
//        // Given: Um request que simula erro desconhecido
//        LoanSimulatorRequestDTO request = LoanSimulatorMock.getRequestSimulatorAge25OrLess();
//
//        when(messageConfig.getMessage(INTERNAL_SERVER_ERROR)).thenReturn("Erro inesperado.");
//
//        // Simulando uma exceção genérica no messageConfig
//        doThrow(new RuntimeException("Erro desconhecido"))
//                .when(messageConfig).getMessage(INTERNAL_SERVER_ERROR);
//
//        // When / Then: Validando se a exceção é lançada corretamente
//        InternalServerErrorException exception = assertThrows(InternalServerErrorException.class, () -> {
//            service.getSimulator(request); // Chamando o método real que gera a exceção
//        });
//
//        // Validação da mensagem da exceção
//        assertEquals("Erro inesperado.", exception.getMessage());
//        verify(messageConfig).getMessage(INTERNAL_SERVER_ERROR);
//    }

}
