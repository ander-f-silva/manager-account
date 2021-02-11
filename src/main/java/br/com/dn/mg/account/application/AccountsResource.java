package br.com.dn.mg.account.application;

import br.com.dn.mg.account.application.payload.DepositAccountDTO;
import br.com.dn.mg.account.application.payload.NewAccountDTO;
import br.com.dn.mg.account.application.payload.TransferAccountDTO;
import br.com.dn.mg.account.domain.usecases.DepositingAccount;
import br.com.dn.mg.account.domain.usecases.GettingAccount;
import br.com.dn.mg.account.domain.usecases.RegisteringNewAccount;
import br.com.dn.mg.account.domain.usecases.TransferringAccount;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.UUIDSerializer;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

@Controller("/accounts")
class AccountsResource {
    @Inject RegisteringNewAccount registeringNewAccount;
    @Inject DepositingAccount depositingAccount;
    @Inject GettingAccount gettingAccount;
    @Inject TransferringAccount transferringAccount;

    @Consumes(MediaType.APPLICATION_JSON)
    @Post
    public HttpResponse<?> registerNewAccount(@Valid @Body NewAccountDTO newAccount) {
        var id =  registeringNewAccount.effect(newAccount);
        return HttpResponse.created(URI.create("/accounts/" + id.toString()));
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @Patch("/{id}/deposit")
    public HttpResponse<?> depositValueInAccount(@PathVariable("id") UUID id, @Valid @Body DepositAccountDTO depositAccount) {
        depositingAccount.effect(id, depositAccount);
        return HttpResponse.noContent();
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Get("/{id}")
    public HttpResponse<?> getAccount(@PathVariable("id") UUID id) {
        return HttpResponse.ok(gettingAccount.find(id));
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Get("/{id}/transactions")
    public HttpResponse<?> getAccountWithTransactions(@PathVariable("id") UUID id) {
        return HttpResponse.ok(gettingAccount.findWithTransactions(id));
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @Patch("/{id}/transfer")
    public HttpResponse<?> transferValueToAccount(@JsonSerialize(contentUsing= UUIDSerializer.class)  @PathVariable("id") UUID toAccountId, @Valid @Body TransferAccountDTO transferAccount) {
        transferringAccount.effect(toAccountId, transferAccount);
        return HttpResponse.noContent();
    }
}
