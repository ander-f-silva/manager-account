package br.com.dn.mg.account.domain.usecases;

import br.com.dn.mg.account.application.payload.NewAccountDTO;

import java.util.UUID;

public interface RegisteringNewAccount {
   UUID effect(NewAccountDTO newAccountDTO);
}
