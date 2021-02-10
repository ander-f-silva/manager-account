package br.com.dn.mg.account.application.payload;

import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.UUID;

@Introspected
public class TransferAccountDTO {
    @NotNull
    private UUID accountFrom;

    @Positive
    private Double value;

    public TransferAccountDTO() {
    }

    public TransferAccountDTO(@NotNull UUID accountFrom, @Positive Double value) {
        this.accountFrom = accountFrom;
        this.value = value;
    }

    public UUID getAccountFrom() {
        return accountFrom;
    }

    public Double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "TransferAccountDTO{" +
                "accountFrom=" + accountFrom +
                ", value=" + value +
                '}';
    }
}
