package br.com.dn.mg.account.domain.usecases;

import br.com.dn.mg.account.application.payload.ListAccountDTO;
import br.com.dn.mg.account.infrastructure.AccountEntity;
import br.com.dn.mg.account.infrastructure.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Singleton
class ListAccount implements ListingAccount {
  private final Logger logger = LoggerFactory.getLogger(ListAccount.class);

  private final AccountRepository accountRepository;

  public ListAccount(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  @Override
  public List<ListAccountDTO> findAll() {
    Stream<AccountEntity> streamAccount =
        StreamSupport.stream(accountRepository.findAll().spliterator(), false);

    logger.info("[List Accounts] find all successfully performs.");

    return streamAccount
        .map(
            accountEntity -> {
              var document = new String(Base64.getDecoder().decode(accountEntity.getDocument()));

              return new ListAccountDTO(
                  accountEntity.getId(), document, accountEntity.getFullName());
            })
        .collect(Collectors.toList());
  }
}
