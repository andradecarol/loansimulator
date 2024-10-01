package com.simulator.loan.application.adapters.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simulator.loan.domain.dto.request.LoanSimulatorRequestDTO;
import com.simulator.loan.mock.LoanSimulatorMock;
import com.simulator.loan.ports.LoanSimulationServicePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LoanSimulationController.class)
public class LoanSimulationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoanSimulationServicePort service;

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
    void shouldReturnAcceptedWhenValidRequest() throws Exception {
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
    void shoultReturnBadRequestWhenInvalidRequest() throws Exception {
        LoanSimulatorRequestDTO invalidRequest = new LoanSimulatorRequestDTO();

        mockMvc.perform(post("/simulator")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shoultReturnBadRequestWhenFutureBirthDate() throws Exception {
        LoanSimulatorRequestDTO request = LoanSimulatorMock.getRequestSimulatorInvalidBirthDate();

        mockMvc.perform(post("/simulator")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

}
