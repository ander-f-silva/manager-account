package br.com.dn.mg.account.application.handlers;

import br.com.dn.mg.account.domain.usecases.errors.InsufficientBalanceException;
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
@Requires(classes = {InsufficientBalanceException.class, ExceptionHandler.class})
public class InsufficientBalanceExceptionHandler
    implements ExceptionHandler<InsufficientBalanceException, HttpResponse> {

  @Override
  public HttpResponse handle(HttpRequest request, InsufficientBalanceException exception) {
    var message = new HashMap<String, String>();
    message.put("error", exception.getMessage());
    return HttpResponse.status(HttpStatus.UNPROCESSABLE_ENTITY).body(message);
  }
}
