package br.com.dn.mg.account.domain.usecases.errors;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(String message) {
        super(message);
    }
}
