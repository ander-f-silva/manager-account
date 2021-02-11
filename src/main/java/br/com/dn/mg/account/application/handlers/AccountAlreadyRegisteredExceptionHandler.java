package br.com.dn.mg.account.application.handlers;

import br.com.dn.mg.account.domain.usecases.errors.AccountAlreadyRegisteredException;
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
@Requires(classes = {AccountAlreadyRegisteredException.class, ExceptionHandler.class})
public class AccountAlreadyRegisteredExceptionHandler
    implements ExceptionHandler<AccountAlreadyRegisteredException, HttpResponse> {
  @Override
  public HttpResponse handle(HttpRequest request, AccountAlreadyRegisteredException exception) {
   var message = new HashMap<String, String>();
   message.put("error", exception.getMessage());
    return HttpResponse.status(HttpStatus.CONFLICT).body(message);
  }
}
