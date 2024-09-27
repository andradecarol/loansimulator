package com.simulator.loan.application.controllers.swagger;

import com.simulator.loan.domain.dto.request.LoanSimulatorRequestDTO;
import com.simulator.loan.domain.dto.response.LoanSimulatorResponseDTO;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

@RequestMapping(value = "/simulator", produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(name = "Simulador", description = "Simulador de crédito que demonstra as condições de pagamento baseadas no valor solicitado, taxa de juros e prazo de pagamento.")
public interface LoanSimulationControllerSwagger {

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resposta bem-sucedida"),
            @ApiResponse(responseCode = "400", description = "Bad Request - Valida os atributos obrigatórios de acordo com as definições do swagger."),
            @ApiResponse(responseCode = "401", description = "Unauthorized - O cliente não possui credenciais de autenticação válidas."),
            @ApiResponse(responseCode = "403", description = "Forbidden - O servidor está se recusando a responder. Isso geralmente é causado por escopos de acesso incorretos"),
            @ApiResponse(responseCode = "404", description = "Not Found - O recurso solicitado não foi encontrado"),
            @ApiResponse(responseCode = "422", description = "Unprocessable Entity - O corpo da solicitação contém erros semânticos. Isso geralmente é causado por não atender à regra de negócios do domínio."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error - Ocorreu um erro interno."),
            @ApiResponse(responseCode = "503", description = "Service Unavailable - O servidor não está disponível no momento."),
            @ApiResponse(responseCode = "504", description = "Gateway Timeout - A solicitação não pôde ser concluída.")
    })
    @PostMapping
    ResponseEntity<LoanSimulatorResponseDTO> getSimulator(
            @RequestBody LoanSimulatorRequestDTO request);
}
