package br.com.dn.mg.account.application.payload;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.UUIDDeserializer;
import com.fasterxml.jackson.databind.ser.std.UUIDSerializer;
import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.UUID;

@Introspected
public class TransferAccountDTO {
    @NotNull
    @JsonSerialize(contentUsing= UUIDSerializer.class)
    @JsonDeserialize(contentUsing= UUIDDeserializer.class)
    private UUID accountFrom;

    @Positive
    private Double value;

    public TransferAccountDTO() {
    }

    public TransferAccountDTO(UUID accountFrom, Double value) {
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
                "accountFrom=" + accountFrom.toString() +
                ", value=" + value +
                '}';
    }
}
