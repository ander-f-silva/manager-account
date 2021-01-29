package br.com.dn.mg.account.domain.usecases;

import br.com.dn.mg.account.application.payload.AccountDTO;
import br.com.dn.mg.account.domain.usecases.errors.AccountNotFoundException;
import br.com.dn.mg.account.infrastructure.AccountRepository;

import javax.inject.Singleton;
import java.util.UUID;

@Singleton
class GetAccount implements GettingAccount {
    private AccountRepository accountRepository;

    public GetAccount(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDTO find(UUID id) {
      return accountRepository.findById(id)
                .map(accountEntity ->
                    new AccountDTO(
                            accountEntity.getDocument(),
                            accountEntity.getFullName(),
                            accountEntity.getAmount()
                    )
                ).orElseThrow(AccountNotFoundException::new);
    }
}
