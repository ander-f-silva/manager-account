package br.com.dn.mg.account.domain.usecases;

import br.com.dn.mg.account.application.payload.TransferAccountDTO;

import java.util.UUID;

public interface TransferringAccount {
  void effect(UUID toAccountId, TransferAccountDTO transferAccount);
}
