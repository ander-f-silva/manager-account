package br.com.dn.mg.account.domain.usecases;

import br.com.dn.mg.account.application.payload.DepositAccountDTO;
import br.com.dn.mg.account.domain.usecases.errors.AccountNotFoundException;
import br.com.dn.mg.account.infrastructure.AccountRepository;

import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.util.UUID;

@Singleton
class DepositAccount implements DepositingAccount {
    private AccountRepository accountRepository;

    public DepositAccount(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    @Override
    public void effect(UUID id, DepositAccountDTO depositAccount) {
        var optAccountDeposited = accountRepository.findById(id);

        if (!optAccountDeposited.isPresent()) {
            throw new AccountNotFoundException();
        }

        var accountDeposited = optAccountDeposited.get();

        var document = accountDeposited.getDocument();
        var fullName = accountDeposited.getFullName();
        var amount = accountDeposited.getAmount();

        var account =  new Account(document, fullName, amount);
        var total = account.sumAmount(depositAccount.getValue());

        accountDeposited.setAmount(total);

        accountRepository.save(accountDeposited);
    }
}
