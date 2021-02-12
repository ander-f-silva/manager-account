package br.com.dn.mg.account.application.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.micronaut.core.annotation.Introspected;

import java.util.Set;

@Introspected
public class AccountDTO {
  private String document;
  private String fullName;

  @JsonIgnoreProperties private Double balanceAmount;
  @JsonIgnoreProperties private Set<TransactionDTO> transactions;

  public AccountDTO(String document, String fullName, Double balanceAmount) {
    this.document = document;
    this.fullName = fullName;
    this.balanceAmount = balanceAmount;
  }

  public AccountDTO(String document, String fullName, Set<TransactionDTO> transactions) {
    this.document = document;
    this.fullName = fullName;
    this.transactions = transactions;
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

  public Set<TransactionDTO> getTransactions() {
    return transactions;
  }

  @Override
  public String toString() {
    return "AccountDTO{"
        + "document='"
        + document
        + '\''
        + ", fullName='"
        + fullName
        + '\''
        + ", balanceAmount="
        + balanceAmount
        + ", transactions="
        + transactions
        + '}';
  }
}
