package br.com.dn.mg.account.domain.usecases;

import br.com.dn.mg.account.application.payload.DepositAccountDTO;

import java.util.UUID;

public interface DepositingAccount {
  void effect(UUID id, DepositAccountDTO depositAccount);
}
