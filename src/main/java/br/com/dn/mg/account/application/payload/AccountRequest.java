package br.com.dn.mg.account.application.payload;

import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotBlank;

@Introspected
public class AccountRequest {
    @NotBlank
    private String document;
    @NotBlank
    private String fullName;

    public AccountRequest() {
    }

    public AccountRequest(String document, String fullName) {
        this.document = document;
        this.fullName = fullName;
    }

    public String getDocument() {
        return document;
    }

    public String getFullName() {
        return fullName;
    }
}
