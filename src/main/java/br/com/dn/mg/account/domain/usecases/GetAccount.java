package br.com.dn.mg.account.domain.usecases;

import br.com.dn.mg.account.application.payload.AccountDTO;
import br.com.dn.mg.account.application.payload.TransactionDTO;
import br.com.dn.mg.account.domain.usecases.errors.AccountNotFoundException;
import br.com.dn.mg.account.infrastructure.AccountRepository;

import javax.inject.Singleton;
import java.util.UUID;
import java.util.stream.Collectors;

@Singleton
class GetAccount implements GettingAccount {
  private final AccountRepository accountRepository;

  public GetAccount(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  @Override
  public AccountDTO find(UUID id) {
    return accountRepository
        .findById(id)
        .map(
            accountEntity ->
                new AccountDTO(
                    accountEntity.getDocument(),
                    accountEntity.getFullName(),
                    accountEntity.getAmount()))
        .orElseThrow(AccountNotFoundException::new);
  }

  @Override
  public AccountDTO findWithTransactions(UUID id) throws AccountNotFoundException {
    return accountRepository
        .search(id)
        .map(
            accountEntity -> {
              var transactions =
                  accountEntity.getTransactions().stream()
                      .map(
                          transactionEntity ->
                              new TransactionDTO(
                                  transactionEntity.getTransactionType().name(),
                                  transactionEntity.getValue()))
                      .collect(Collectors.toSet());

              return new AccountDTO(
                  accountEntity.getDocument(), accountEntity.getFullName(), transactions);
            })
        .orElseThrow(AccountNotFoundException::new);
  }
}
