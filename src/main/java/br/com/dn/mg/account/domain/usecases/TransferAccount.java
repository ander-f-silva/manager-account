package br.com.dn.mg.account.domain.usecases;

import br.com.dn.mg.account.application.payload.TransferAccountDTO;

import javax.inject.Singleton;
import java.util.UUID;

@Singleton
public class TransferAccount implements TransferringAccount {
    public void effect(UUID toAccountId, TransferAccountDTO transferAccount) {

    }
}
