package br.com.dn.mg.account.domain.usecases;


import br.com.dn.mg.account.application.payload.AccountDTO;
import br.com.dn.mg.account.domain.usecases.errors.AccountNotFoundException;

import java.util.UUID;

public interface GettingAccount {
    AccountDTO find(UUID id) throws AccountNotFoundException;
}
