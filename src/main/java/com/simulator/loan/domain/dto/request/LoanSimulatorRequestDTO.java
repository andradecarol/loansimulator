package com.simulator.loan.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.simulator.loan.domain.exceptions.MessageErrorCodeConstants.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LoanSimulatorRequestDTO {


    @Positive(message = FIELD_MUST_BE_POSITIVE)
    @NotNull(message = FIELD_NOT_BE_NULL)
    private BigDecimal amount;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Schema(description = "Data de nascimento no formato dd/MM/yyyy", example = "16/04/1991")
    @Past(message = FIELD_MUST_BE_DATE_IN_PAST)
    @NotNull(message = FIELD_NOT_BE_NULL)
    private LocalDate birthDate ;


    @Positive(message = FIELD_MUST_BE_POSITIVE)
    @Digits(integer = 3, fraction = 0, message = FIELD_MUST_BE_VALID)
    @NotNull(message = FIELD_NOT_BE_NULL)
    private Integer months;

    public LoanSimulatorRequestDTO toLoanSimulator(){
        return new LoanSimulatorRequestDTO(amount, birthDate, months);
    }

}
