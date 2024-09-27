package com.simulator.loan.domain.services;

import com.simulator.loan.domain.dto.request.LoanSimulatorRequestDTO;
import com.simulator.loan.domain.dto.response.LoanSimulatorResponseDTO;
import com.simulator.loan.domain.services.interfaces.LoanSimulationServiceInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoanSimulationService implements LoanSimulationServiceInterface {
    @Override
    public LoanSimulatorResponseDTO getSimulator(LoanSimulatorRequestDTO request) {
        try{
            var idade = Period.between(request.getBirthDate(), LocalDate.now()).getYears(); // transformando data de nascimento em idade
            var taxaJurosAno = calculoJurosAno(idade); // calculando a taxa de juros ao ano
            RoundingMode arr = RoundingMode.HALF_UP; // arredonda para cima se o valor estiver no meio

            BigDecimal taxaJurosMensal = taxaJurosAno.divide(BigDecimal.valueOf(12), 4, arr); // calculando juros mensal

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

        }catch (Exception e){
            log.info(e.getMessage());
            throw e;
        }
    }

    private BigDecimal calculoJurosAno(Integer age){
        if (age <= 25) {
            return BigDecimal.valueOf(0.05); // até 25 anos
        } else if (age <= 40) {
            return BigDecimal.valueOf(0.03); // de 26 a 40 anos
        } else if (age <= 60) {
            return BigDecimal.valueOf(0.02); // de 41 a 60 anos
        } else {
            return BigDecimal.valueOf(0.04); // acima de 60 anos
        }
    }

    private BigDecimal calculoParcelaFixa(BigDecimal valorEmprestimo, BigDecimal taxaJurosMensal, Integer qtdMeses ){
        RoundingMode arr = RoundingMode.HALF_UP; // arredonda para cima se o valor estiver no meio

        BigDecimal um = BigDecimal.ONE;
        BigDecimal dividendo = valorEmprestimo.multiply(taxaJurosMensal);
        BigDecimal divisorBase = um.add(taxaJurosMensal);
        BigDecimal divisorPotencia = divisorBase.pow(qtdMeses);
        BigDecimal divisorTotal = um.subtract(um.divide(divisorPotencia, 4, arr));

        return dividendo.divide(divisorTotal, 2, arr);
    }

}
