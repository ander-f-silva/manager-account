package br.com.dn.mg.account.infrastructure;

import io.micronaut.context.annotation.Executable;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends CrudRepository<AccountEntity, UUID> {
  @Executable
  Boolean existsByDocument(String document);

  @Executable
  Optional<AccountEntity> findByDocument(String document);

  @Executable
  @Query("select a from account a join fetch a.transactions t where a.id = :id ")
  Optional<AccountEntity> search(UUID id);
}
