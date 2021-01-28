package br.com.dn.mg.account.infrastructure;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "account")
public class AccountEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID id;

    private String document ;

    @Column(name = "full_name")
    private String fullName;


    private Double amount;

    @CreationTimestamp
    @Column(name = "created_At")
    private LocalDateTime createdAt;

    public AccountEntity() {
    }

    public AccountEntity(String document, String fullName) {
        this.document = document;
        this.fullName = fullName;
        this.amount = 0.0;
    }

    public UUID getId() {
        return id;
    }

    public String getDocument() {
        return document;
    }

    public String getFullName() {
        return fullName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", document='" + document + '\'' +
                ", fullName='" + fullName + '\'' +
                ", amount=" + amount +
                ", createdAt=" + createdAt +
                '}';
    }
}
