package br.com.dn.mg.account.domain.usecases;

import br.com.dn.mg.account.application.payload.AccountDTO;

import java.util.UUID;

public interface GettingAccount {
    AccountDTO find(UUID id);

    AccountDTO findWithTransactions(UUID id);
}
