package br.com.dn.mg.account.infrastructure;

import io.micronaut.context.annotation.Executable;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface AccountRepository extends CrudRepository<AccountEntity, UUID> {
    @Executable
    Boolean existsByDocument(String document);

    @Executable
    Optional<AccountEntity> findByDocument(String document);

    @Executable
    @Join(value = "transactions", type = Join.Type.RIGHT)
    Set<AccountEntity> findByAccountWithTransactions(UUID id);
}
