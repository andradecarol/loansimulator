package com.simulator.loan.domain.dto.response;

import com.simulator.loan.config.application.MessageConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ErrorResponse {

    private String codigo;

    private String mensagem;

    public ErrorResponse(String codigo, Object... args) {
        this.codigo = codigo;
        this.mensagem = new MessageConfig().getMessage(codigo, args);
    }
}