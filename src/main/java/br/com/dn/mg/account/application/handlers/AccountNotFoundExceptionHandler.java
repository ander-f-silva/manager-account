package br.com.dn.mg.account.application.handlers;

import br.com.dn.mg.account.domain.usecases.errors.AccountNotFoundException;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;

import javax.inject.Singleton;
import java.util.HashMap;

@Produces
@Singleton
@Requires(classes = {AccountNotFoundException.class, ExceptionHandler.class})
public class AccountNotFoundExceptionHandler
    implements ExceptionHandler<AccountNotFoundException, HttpResponse> {
  @Override
  public HttpResponse handle(HttpRequest request, AccountNotFoundException exception) {
    var message = new HashMap<String, String>();
    message.put("error", exception.getMessage());
    return HttpResponse.notFound(message);
  }
}
