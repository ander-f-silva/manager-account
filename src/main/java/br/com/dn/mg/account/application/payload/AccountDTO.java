package br.com.dn.mg.account.application.payload;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class AccountDTO {
    private String document;
    private String fullName;
    private Double balanceAmount;

    public AccountDTO(String document, String fullName, Double balanceAmount) {
        this.document = document;
        this.fullName = fullName;
        this.balanceAmount = balanceAmount;
    }

    public String getDocument() {
        return document;
    }

    public String getFullName() {
        return fullName;
    }

    public Double getBalanceAmount() {
        return balanceAmount;
    }

    @Override
    public String toString() {
        return "AccountDTO{" +
                "document='" + document + '\'' +
                ", fullName='" + fullName + '\'' +
                ", balanceAmount=" + balanceAmount +
                '}';
    }
}
