package br.com.dn.mg.account.domain;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import br.com.dn.mg.account.application.payload.NewAccountDTO;
import br.com.dn.mg.account.domain.errors.AccountAlreadyRegisteredException;
import br.com.dn.mg.account.domain.errors.InvalidDocumentException;
import br.com.dn.mg.account.infrastructure.Account;
import br.com.dn.mg.account.infrastructure.AccountRepository;

import javax.inject.Singleton;
import java.util.UUID;

@Singleton
class RegisterNewAccount implements RegisteringNewAccount {

    AccountRepository repository;

    public RegisterNewAccount(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public UUID effect(NewAccountDTO newAccount) {
        CPFValidator validator = new CPFValidator();

        try {
            validator.assertValid(newAccount.getDocument());
        } catch (InvalidStateException e) {
           throw new InvalidDocumentException();
        }

        var hasRegisteredAccount = repository.existsByDocument(newAccount.getDocument());
        if (hasRegisteredAccount) {
           throw new AccountAlreadyRegisteredException();
        }

        var account = repository.save(new Account(newAccount.getDocument(), newAccount.getFullName()));
        return account.getId();

    }
}
