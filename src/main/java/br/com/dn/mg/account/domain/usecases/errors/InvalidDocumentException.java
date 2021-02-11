package br.com.dn.mg.account.domain.usecases.errors;

public class InvalidDocumentException extends RuntimeException {
    public InvalidDocumentException(String message) {
        super(message);
    }
}
