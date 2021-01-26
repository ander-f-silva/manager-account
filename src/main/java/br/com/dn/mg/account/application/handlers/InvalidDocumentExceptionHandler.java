package br.com.dn.mg.account.application.handlers;

import br.com.dn.mg.account.domain.errors.InvalidDocumentException;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;

import javax.inject.Singleton;

@Produces
@Singleton
@Requires(classes = {InvalidDocumentException.class, ExceptionHandler.class})
public class InvalidDocumentExceptionHandler implements ExceptionHandler<InvalidDocumentException, HttpResponse> {

    @Override
    public HttpResponse handle(HttpRequest request, InvalidDocumentException exception) {
        return HttpResponse.status(HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
