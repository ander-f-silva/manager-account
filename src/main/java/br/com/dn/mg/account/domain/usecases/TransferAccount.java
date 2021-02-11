package br.com.dn.mg.account.domain.usecases;

import br.com.dn.mg.account.application.payload.TransferAccountDTO;
import br.com.dn.mg.account.domain.usecases.errors.AccountNotFoundException;
import br.com.dn.mg.account.domain.usecases.errors.InsufficientBalanceException;
import br.com.dn.mg.account.infrastructure.AccountRepository;

import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.util.UUID;

@Singleton
public class TransferAccount implements TransferringAccount {
  private final AccountRepository accountRepository;

  public TransferAccount(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  @Transactional
  @Override
  public void effect(UUID toAccountId, TransferAccountDTO transferAccount) {
    withdrawAccount(toAccountId, transferAccount);
    depositAccount(transferAccount);
  }

  private void depositAccount(TransferAccountDTO transferAccount) {
    var fromAccountEntity = accountRepository.findById(transferAccount.getAccountFrom()).orElseThrow(() -> new AccountNotFoundException());

    var account = new Account(fromAccountEntity.getAmount());
    var total = account.deposit(transferAccount.getValue());

    fromAccountEntity.setAmount(total);
    accountRepository.save(fromAccountEntity);
  }

  private void withdrawAccount(UUID toAccountId, TransferAccountDTO transferAccount) {
    var toAccountEntity = accountRepository.findById(toAccountId).orElseThrow(() -> new AccountNotFoundException());

    var account = new Account(toAccountEntity.getAmount());
    var total = account.withdraw(transferAccount.getValue());

    if (total <= 0.0) {
      throw new InsufficientBalanceException();
    }

    toAccountEntity.setAmount(total);
    accountRepository.save(toAccountEntity);
  }


}
