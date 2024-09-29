package com.simulator.loan.domain.services;

import com.simulator.loan.config.application.MessageConfig;
import com.simulator.loan.domain.dto.request.LoanSimulatorRequestDTO;
import com.simulator.loan.domain.dto.response.LoanSimulatorResponseDTO;
import com.simulator.loan.domain.exceptions.InternalServerErrorException;
import com.simulator.loan.domain.exceptions.MessageErrorCodeConstants;
import com.simulator.loan.domain.exceptions.UnprocessableEntityException;
import com.simulator.loan.domain.services.interfaces.LoanSimulationServiceInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;

import static com.simulator.loan.domain.exceptions.MessageErrorCodeConstants.INTERNAL_SERVER_ERROR;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoanSimulationService implements LoanSimulationServiceInterface {

    private final MessageConfig messageConfig;

    @Override
    public LoanSimulatorResponseDTO getSimulator(LoanSimulatorRequestDTO request) {
        try{
            log.info("Dados recebidos: {}", request);

            var idade = Period.between(request.getBirthDate(), LocalDate.now()).getYears();
            var taxaJurosAno = calculoJurosAno(idade);
            log.info("Taxa de juros calculada: {}", taxaJurosAno);

            RoundingMode arr = RoundingMode.HALF_UP;

            BigDecimal taxaJurosMensal = taxaJurosAno.divide(BigDecimal.valueOf(12), 4, arr);

            var valorEmprestimo = request.getAmount();
            var qtdMeses = request.getMonths();

            BigDecimal parcelaFixaMensal = calculoParcelaFixa(valorEmprestimo, taxaJurosMensal, qtdMeses);
            BigDecimal valorTotalAPagar = parcelaFixaMensal.multiply(BigDecimal.valueOf(qtdMeses));
            BigDecimal jurosTotal = valorTotalAPagar.subtract(valorEmprestimo);

            return LoanSimulatorResponseDTO.builder()
                    .jurosTotalAPagar(jurosTotal)
                    .parcelasMensais(parcelaFixaMensal)
                    .valorTotalAPagar(valorTotalAPagar)
                    .build();

        }catch (UnprocessableEntityException ex) {
            log.error(messageConfig.getMessage(MessageErrorCodeConstants.BUSINESS_ERROR), ex.getMessage(), ex.toString());
            throw ex;
        }catch (Exception ex) {
            log.error(messageConfig.getMessage(INTERNAL_SERVER_ERROR), ex.getMessage(), ex.toString());
            throw new InternalServerErrorException(INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    private BigDecimal calculoJurosAno(Integer age){
        log.info("Calculando taxa de juros conforme idade...");
        if (age <= 25) {
            return BigDecimal.valueOf(0.05);
        } else if (age <= 40) {
            return BigDecimal.valueOf(0.03);
        } else if (age <= 60) {
            return BigDecimal.valueOf(0.02);
        } else {
            return BigDecimal.valueOf(0.04);
        }
    }

    private BigDecimal calculoParcelaFixa(BigDecimal valorEmprestimo, BigDecimal taxaJurosMensal, Integer qtdMeses ){
        RoundingMode arr = RoundingMode.HALF_UP;

        BigDecimal um = BigDecimal.ONE;
        BigDecimal dividendo = valorEmprestimo.multiply(taxaJurosMensal);
        BigDecimal divisorBase = um.add(taxaJurosMensal);
        BigDecimal divisorPotencia = divisorBase.pow(qtdMeses);
        BigDecimal divisorTotal = um.subtract(um.divide(divisorPotencia, 4, arr));

        return dividendo.divide(divisorTotal, 2, arr);
    }

}
