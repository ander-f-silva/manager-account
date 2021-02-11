package br.com.dn.mg.account.domain.usecases;

import br.com.dn.mg.account.application.payload.TransferAccountDTO;
import br.com.dn.mg.account.domain.usecases.errors.AccountNotFoundException;
import br.com.dn.mg.account.domain.usecases.errors.InsufficientBalanceException;
import br.com.dn.mg.account.infrastructure.AccountRepository;
import br.com.dn.mg.account.infrastructure.TransactionEntity;
import br.com.dn.mg.account.infrastructure.TransactionRepository;
import br.com.dn.mg.account.infrastructure.TransactionType;

import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.util.UUID;

@Singleton
public class TransferAccount implements TransferringAccount {
  private final AccountRepository accountRepository;
  private final TransactionRepository transactionRepository;

  public TransferAccount(
      AccountRepository accountRepository, TransactionRepository transactionRepository) {
    this.accountRepository = accountRepository;
    this.transactionRepository = transactionRepository;
  }

  @Transactional
  @Override
  public void effect(UUID toAccountId, TransferAccountDTO transferAccount) {
    withdrawAccount(toAccountId, transferAccount);
    depositAccount(transferAccount);
  }

  private void depositAccount(TransferAccountDTO transferAccount) {
    var fromAccount =
        accountRepository
            .findById(transferAccount.getAccountFrom())
            .orElseThrow(() -> new AccountNotFoundException());

    var account = new Account(fromAccount.getAmount());
    var total = account.deposit(transferAccount.getValue());

    transactionRepository.save(new TransactionEntity(fromAccount, TransactionType.TRANSFER, transferAccount.getValue()));

    fromAccount.setAmount(total);
    accountRepository.save(fromAccount);
  }

  private void withdrawAccount(UUID toAccountId, TransferAccountDTO transferAccount) {
    var toAccount =
        accountRepository.findById(toAccountId).orElseThrow(() -> new AccountNotFoundException());

    var account = new Account(toAccount.getAmount());
    var total = account.withdraw(transferAccount.getValue());

    if (total <= 0.0) {
      throw new InsufficientBalanceException();
    }

    transactionRepository.save(new TransactionEntity(toAccount, TransactionType.TRANSFER, transferAccount.getValue() * -1));

    toAccount.setAmount(total);
    accountRepository.save(toAccount);
  }
}
