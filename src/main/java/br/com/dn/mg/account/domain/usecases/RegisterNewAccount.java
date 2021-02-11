package br.com.dn.mg.account.domain.usecases;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import br.com.dn.mg.account.application.payload.NewAccountDTO;
import br.com.dn.mg.account.domain.usecases.errors.AccountAlreadyRegisteredException;
import br.com.dn.mg.account.domain.usecases.errors.InvalidDocumentException;
import br.com.dn.mg.account.infrastructure.AccountEntity;
import br.com.dn.mg.account.infrastructure.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.util.Base64;
import java.util.UUID;

@Singleton
class RegisterNewAccount implements RegisteringNewAccount {
  private final Logger logger = LoggerFactory.getLogger(RegisterNewAccount.class);

  private final AccountRepository repository;

  public RegisterNewAccount(AccountRepository repository) {
    this.repository = repository;
  }

  @Transactional
  @Override
  public UUID effect(NewAccountDTO newAccount) {
    CPFValidator validator = new CPFValidator();

    try {
      validator.assertValid(newAccount.getDocument());
    } catch (InvalidStateException e) {
      logger.error("[Account: {}] The document informed is invalid", newAccount);
      throw new InvalidDocumentException("The document informed is invalid.");
    }

    var encodedDocument = Base64.getEncoder().encodeToString(newAccount.getDocument().getBytes());

    var hasRegisteredAccount = repository.existsByDocument(encodedDocument);
    if (hasRegisteredAccount) {
      logger.error("[Account: {}] The account has already been registered", newAccount);
      throw new AccountAlreadyRegisteredException("The account has already been registered.");
    }

    var account = repository.save(new AccountEntity(encodedDocument, newAccount.getFullName()));

    logger.info("[Account: {}] Successful register", newAccount);

    return account.getId();
  }
}
