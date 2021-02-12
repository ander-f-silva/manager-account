package br.com.dn.mg.account.application.payload;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.UUIDDeserializer;
import com.fasterxml.jackson.databind.ser.std.UUIDSerializer;
import io.micronaut.core.annotation.Introspected;

import java.util.UUID;

@Introspected
public class ListAccountDTO {
    @JsonSerialize(contentUsing = UUIDSerializer.class)
    @JsonDeserialize(contentUsing = UUIDDeserializer.class)
    private UUID id;
    private String document;
    private String fullName;

    public ListAccountDTO() {
    }

    public ListAccountDTO(UUID id, String document, String fullName) {
        this.id = id;
        this.document = document;
        this.fullName = fullName;
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

    @Override
    public String toString() {
        return "ListAccountDTO{" +
                "id=" + id +
                ", document='" + document + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
