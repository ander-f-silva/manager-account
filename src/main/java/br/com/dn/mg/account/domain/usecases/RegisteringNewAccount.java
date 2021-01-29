package br.com.dn.mg.account.domain.usecases;


import br.com.dn.mg.account.application.payload.NewAccountDTO;
import br.com.dn.mg.account.domain.usecases.errors.AccountAlreadyRegisteredException;
import br.com.dn.mg.account.domain.usecases.errors.InvalidDocumentException;

import java.util.UUID;

public interface RegisteringNewAccount {
   UUID effect(NewAccountDTO newAccountDTO) throws InvalidDocumentException, AccountAlreadyRegisteredException;
}
