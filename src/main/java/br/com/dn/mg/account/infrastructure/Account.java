package br.com.dn.mg.account.infrastructure;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Account {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    private String document ;

    @Column(name = "full_name")
    private String fullName;


    private Double amount;

    @CreationTimestamp
    @Column(name = "created_At")
    private LocalDateTime createdAt;

    public Account() {
    }

    public Account(String document, String fullName) {
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