package com.simulator.loan.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.simulator.loan.domain.exceptions.MessageErrorCodeConstants.FIELD_MUST_BE_VALID;
import static com.simulator.loan.domain.exceptions.MessageErrorCodeConstants.FIELD_NOT_BE_NULL;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LoanSimulatorRequestDTO {


    @Digits(integer = 10, fraction = 2, message = FIELD_MUST_BE_VALID)
    @NotNull(message = FIELD_NOT_BE_NULL)
    private BigDecimal amount;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Schema(description = "Data de nascimento no formato dd/MM/yyyy", example = "16/04/1991")
    @NotNull(message = FIELD_NOT_BE_NULL)
    private LocalDate birthDate ;


    @Digits(integer = 3, fraction = 0, message = FIELD_MUST_BE_VALID)
    @NotNull(message = FIELD_NOT_BE_NULL)
    private Integer months;

    public LoanSimulatorRequestDTO toLoanSimulator(){
        return new LoanSimulatorRequestDTO(amount, birthDate, months);
    }

}
