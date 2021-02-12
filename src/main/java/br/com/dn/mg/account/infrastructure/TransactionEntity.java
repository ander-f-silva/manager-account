package br.com.dn.mg.account.infrastructure;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "transaction")
public class TransactionEntity {
  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Type(type = "org.hibernate.type.UUIDCharType")
  private UUID id;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "account_id")
  private AccountEntity accountEntity;

  @Enumerated(EnumType.STRING)
  @Column(name = "operation")
  private TransactionType transactionType;

  private Double value;

  @CreationTimestamp
  @Column(name = "created_At")
  private LocalDateTime createdAt;

  public TransactionEntity() {}

  public TransactionEntity(
      AccountEntity accountEntity, TransactionType transactionType, Double value) {
    this.accountEntity = accountEntity;
    this.transactionType = transactionType;
    this.value = value;
  }

  public UUID getId() {
    return id;
  }

  public AccountEntity getAccountEntity() {
    return accountEntity;
  }

  public TransactionType getTransactionType() {
    return transactionType;
  }

  public Double getValue() {
    return value;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  @Override
  public String toString() {
    return "TransactionEntity{"
        + "id="
        + id
        + ", accountEntity="
        + accountEntity
        + ", transactionType="
        + transactionType
        + ", value="
        + value
        + ", createdAt="
        + createdAt
        + '}';
  }
}
