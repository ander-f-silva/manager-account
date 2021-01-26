package br.com.dn.mg.account.application;

import br.com.dn.mg.account.application.payload.NewAccountDTO;
import br.com.dn.mg.account.domain.RegisteringNewAccount;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@Controller("/accounts")
class AccountsResource {

    RegisteringNewAccount registeringNewAccount;

    public AccountsResource(RegisteringNewAccount registeringNewAccount) {
        this.registeringNewAccount = registeringNewAccount;
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @Post
    public HttpResponse<?> register(@Valid @Body NewAccountDTO newAccount) {
        var id =  registeringNewAccount.effect(newAccount);
        return HttpResponse.created(URI.create("/accounts/" + id.toString()));
    }

}
