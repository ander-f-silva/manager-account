package br.com.dn.mg.account.application;

import br.com.dn.mg.account.application.payload.AccountRequest;
import br.com.dn.mg.account.infrastructure.Account;
import br.com.dn.mg.account.infrastructure.AccountRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;

@Controller("/accounts")
class AccountsResource {

    AccountRepository repository;

    public AccountsResource(AccountRepository repository) {
        this.repository = repository;
    }

    @Post
    public HttpResponse<?> register(@Valid @Body AccountRequest accountRequest) {
        var account = repository.save(new Account(accountRequest.getDocument(), accountRequest.getFullName()));
        return HttpResponse
                .created(URI.create("/accounts/" + account.getId()));
    }

}
