package br.com.dn.mg.account.infrastructure;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.UUID;

@Repository
public interface TransactionRepository extends CrudRepository<TransactionEntity, UUID> {}
