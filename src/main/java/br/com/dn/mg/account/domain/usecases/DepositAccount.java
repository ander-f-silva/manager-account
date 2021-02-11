package br.com.dn.mg.account.domain.usecases;

import br.com.dn.mg.account.application.payload.DepositAccountDTO;
import br.com.dn.mg.account.domain.usecases.errors.AccountNotFoundException;
import br.com.dn.mg.account.infrastructure.AccountRepository;
import br.com.dn.mg.account.infrastructure.TransactionEntity;
import br.com.dn.mg.account.infrastructure.TransactionRepository;
import br.com.dn.mg.account.infrastructure.TransactionType;

import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.util.UUID;

@Singleton
class DepositAccount implements DepositingAccount {
  private final AccountRepository accountRepository;
  private final TransactionRepository transactionRepository;

  public DepositAccount(
      AccountRepository accountRepository, TransactionRepository transactionRepository) {
    this.accountRepository = accountRepository;
    this.transactionRepository = transactionRepository;
  }

  @Transactional
  @Override
  public void effect(UUID id, DepositAccountDTO depositAccount) {
    var accountDeposited =
        accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException());

    var account = new Account(accountDeposited.getAmount());
    var total = account.deposit(depositAccount.getValue());

    transactionRepository.save(
        new TransactionEntity(
            accountDeposited, TransactionType.DEPOSIT, depositAccount.getValue()));

    accountDeposited.setAmount(total);
    accountRepository.save(accountDeposited);
  }
}
