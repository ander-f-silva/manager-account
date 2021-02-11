package br.com.dn.mg.account.application.payload;

import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotBlank;

@Introspected
public class NewAccountDTO {
    @NotBlank
    private String document;
    @NotBlank
    private String fullName;

    public NewAccountDTO() {
    }

    public NewAccountDTO(String document, String fullName) {
        this.document = document;
        this.fullName = fullName;
    }

    public String getDocument() {
        return document;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public String toString() {
        return "NewAccountDTO{" +
                "document='" + document + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
