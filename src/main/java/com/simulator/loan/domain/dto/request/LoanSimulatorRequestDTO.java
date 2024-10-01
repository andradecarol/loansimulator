package com.simulator.loan.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.simulator.loan.domain.exceptions.MessageErrorCodeConstants.FIELD_MUST_BE_DATE_IN_PAST;
import static com.simulator.loan.domain.exceptions.MessageErrorCodeConstants.FIELD_MUST_BE_POSITIVE;
import static com.simulator.loan.domain.exceptions.MessageErrorCodeConstants.FIELD_MUST_BE_VALID;
import static com.simulator.loan.domain.exceptions.MessageErrorCodeConstants.FIELD_NOT_BE_NULL;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Schema(description = "Dados necessários para simular um empréstimo",
        example = "{ \"amount\": 1000, \"birthDate\": \"16/04/1991\", \"months\": 12 }")
public class LoanSimulatorRequestDTO {

    @Positive(message = FIELD_MUST_BE_POSITIVE)
    @NotNull(message = FIELD_NOT_BE_NULL)
    @DecimalMin(value = "0.0", inclusive = false, message = FIELD_MUST_BE_VALID)
    private BigDecimal amount;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Past(message = FIELD_MUST_BE_DATE_IN_PAST)
    @NotNull(message = FIELD_NOT_BE_NULL)
    private LocalDate birthDate;

    @Positive(message = FIELD_MUST_BE_POSITIVE)
    @Min(value = 1, message = FIELD_MUST_BE_VALID)
    @Digits(integer = 3, fraction = 0, message = FIELD_MUST_BE_VALID)
    @NotNull(message = FIELD_NOT_BE_NULL)
    private Integer months;

}
