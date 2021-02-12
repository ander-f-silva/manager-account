package br.com.dn.mg.account.application.handlers;

import br.com.dn.mg.account.domain.usecases.errors.InvalidDocumentException;
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
@Requires(classes = {InvalidDocumentException.class, ExceptionHandler.class})
public class InvalidDocumentExceptionHandler
    implements ExceptionHandler<InvalidDocumentException, HttpResponse> {

  @Override
  public HttpResponse handle(HttpRequest request, InvalidDocumentException exception) {
    var message = new HashMap<String, String>();
    message.put("error", exception.getMessage());
    return HttpResponse.status(HttpStatus.UNPROCESSABLE_ENTITY).body(message);
  }
}
