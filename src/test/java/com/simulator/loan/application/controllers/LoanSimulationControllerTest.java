package com.simulator.loan.application.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simulator.loan.domain.dto.request.LoanSimulatorRequestDTO;
import com.simulator.loan.domain.dto.response.LoanSimulatorResponseDTO;
import com.simulator.loan.domain.services.interfaces.LoanSimulationServiceInterface;
import com.simulator.loan.mock.LoanSimulatorMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LoanSimulationController.class)
public class LoanSimulationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoanSimulationServiceInterface service;

    @Autowired
    private ObjectMapper objectMapper;

    @InjectMocks
    private LoanSimulationController controller;

    private LoanSimulatorRequestDTO requestDTO;


    @BeforeEach
    void setUp() {
        requestDTO = LoanSimulatorMock.getRequestSimulatorAge26To40();

        var responseDTO = LoanSimulatorMock.getResponseSimulatorAge26To40();

        when(service.getSimulator(requestDTO)).thenReturn(responseDTO);
    }

    @Test
    public void shouldReturnAcceptedWhenValidRequest() throws Exception {
        var mockResponse = LoanSimulatorMock.getResponseSimulatorAge26To40();
        mockMvc.perform(post("/simulator")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.jurosTotalAPagar").value(mockResponse.getJurosTotalAPagar().doubleValue()))
                .andExpect(jsonPath("$.parcelasMensais").value(mockResponse.getParcelasMensais().doubleValue()))
                .andExpect(jsonPath("$.valorTotalAPagar").value(mockResponse.getValorTotalAPagar().doubleValue()));
    }

    @Test
    public void shoultReturnBadRequestWhenInvalidRequest() throws Exception {
        LoanSimulatorRequestDTO invalidRequest = new LoanSimulatorRequestDTO();

        mockMvc.perform(post("/simulator")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shoultReturnBadRequestWhenFutureBirthDate() throws Exception {
        LoanSimulatorRequestDTO request = LoanSimulatorMock.getRequestSimulatorInvalidBirthDate();

        mockMvc.perform(post("/simulator")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

}
