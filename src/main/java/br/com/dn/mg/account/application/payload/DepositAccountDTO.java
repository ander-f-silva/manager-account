package br.com.dn.mg.account.application.payload;

import io.micronaut.core.annotation.Introspected;
import javax.validation.constraints.Positive;

@Introspected
public class DepositAccountDTO {
    @Positive
    private Double value;

    public DepositAccountDTO() {
    }

    public DepositAccountDTO(Double value) {
        this.value = value;
    }

    public Double getValue() {
        return value;
    }
}