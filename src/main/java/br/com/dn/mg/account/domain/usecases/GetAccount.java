package br.com.dn.mg.account.domain.usecases;

import br.com.dn.mg.account.application.payload.AccountDTO;
import br.com.dn.mg.account.application.payload.TransactionDTO;
import br.com.dn.mg.account.domain.usecases.errors.AccountNotFoundException;
import br.com.dn.mg.account.infrastructure.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.util.Base64;
import java.util.UUID;
import java.util.stream.Collectors;

@Singleton
class GetAccount implements GettingAccount {
    private final Logger logger = LoggerFactory.getLogger(GetAccount.class);

    private final AccountRepository accountRepository;

  public GetAccount(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  @Override
  public AccountDTO find(UUID id) {
    return accountRepository
        .findById(id)
        .map(
            accountEntity -> {
              byte[] decodedDocument = Base64.getDecoder().decode(accountEntity.getDocument());
              var document = new String(decodedDocument);

              logger.info("[Account: {}] Research carried out successfully", id.toString());

              return new AccountDTO(
                  document, accountEntity.getFullName(), accountEntity.getAmount());
            })
        .orElseThrow(() -> {
            logger.error("[AccountId: {}] Not found", id.toString());
            return new AccountNotFoundException("The reported account was not found.");
        });
  }

  @Override
  public AccountDTO findWithTransactions(UUID id) {
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

              byte[] decodedDocument = Base64.getDecoder().decode(accountEntity.getDocument());
              var document = new String(decodedDocument);

              logger.info("[Account: {}] Research carried out successfully", id.toString());

              return new AccountDTO(document, accountEntity.getFullName(), transactions);
            })
        .orElseThrow(() -> {
            logger.error("[AccountId: {}] Not found", id.toString());
            return new AccountNotFoundException("The reported account was not found.");
        });
  }
}
