package com.simulator.loan.application.adapters.controllers.swagger;

import com.simulator.loan.domain.dto.request.LoanSimulatorRequestDTO;
import com.simulator.loan.domain.dto.response.LoanSimulatorResponseDTO;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/simulator", produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(name = "Simulador", description = "Simulador de crédito que demonstra as condições de pagamento baseadas no valor solicitado, taxa de juros e prazo de pagamento.")
public interface LoanSimulationControllerSwagger {

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resposta bem-sucedida",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LoanSimulatorResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request - Valida os atributos obrigatórios de acordo com as definições do swagger.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{ \"codigo\": 400, \"mensagem\": \"Requisição inválida\" }"))),
            @ApiResponse(responseCode = "401", description = "Unauthorized - O cliente não possui credenciais de autenticação válidas.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{ \"codigo\": 401, \"mensagem\": \"Não autorizado\" }"))),
            @ApiResponse(responseCode = "403", description = "Forbidden - O servidor está se recusando a responder. Isso geralmente é causado por escopos de acesso incorretos",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{ \"codigo\": 403, \"mensagem\": \"O servidor está se recusando a responder\" }"))),
            @ApiResponse(responseCode = "404", description = "Not Found - O recurso solicitado não foi encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{ \"codigo\": 404, \"mensagem\": \"O recurso solicitado não foi encontrado\" }"))),
            @ApiResponse(responseCode = "422", description = "Unprocessable Entity - O corpo da solicitação contém erros semânticos. Isso geralmente é causado por não atender à regra de negócios do domínio.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{ \"codigo\": 422, \"mensagem\": \"O corpo da solicitação contém erros semânticos.\" }"))),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro interno.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{ \"codigo\": 500, \"mensagem\": \"Erro interno no servidor\" }"))),
            @ApiResponse(responseCode = "503", description = "O servidor não está disponível no momento.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{ \"codigo\": 503, \"mensagem\": \"O servidor não está disponível no momento.\" }"))),
            @ApiResponse(responseCode = "504", description = " solicitação não pôde ser concluída.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{ \"codigo\": 504, \"mensagem\": \"A solicitação não pôde ser concluída.y\" }")))
    })
    @PostMapping
    ResponseEntity<LoanSimulatorResponseDTO> getSimulator(
            @RequestBody @Valid LoanSimulatorRequestDTO request);

}
