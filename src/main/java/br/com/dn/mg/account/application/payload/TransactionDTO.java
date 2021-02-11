package br.com.dn.mg.account.application.payload;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class TransactionDTO {
  private String type;
  private Double value;

  public TransactionDTO() {}

  public TransactionDTO(String type, Double value) {
    this.type = type;
    this.value = value;
  }

  public String getType() {
    return type;
  }

  public Double getValue() {
    return value;
  }

  @Override
  public String toString() {
    return "TransactionDTO{" + "type='" + type + '\'' + ", value=" + value + '}';
  }
}
