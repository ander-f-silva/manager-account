package br.com.dn.mg.account.domain.usecases;

import br.com.dn.mg.account.application.payload.ListAccountDTO;

import java.util.List;

public interface ListingAccount {
    List<ListAccountDTO> findAll();
}
