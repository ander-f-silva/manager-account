package br.com.dn.mg.account.domain.usecases.errors;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
