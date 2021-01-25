package br.com.dn.mg.account.application;

import br.com.dn.mg.account.application.payload.AccountRequest;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;

import javax.inject.Inject;

import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
class AccountsResourceTest {

    @Inject
    @Client("/")
    HttpClient client;

    @DisplayName("should register a new account ")
    @ParameterizedTest(name = "{index} with document ''{0}'' and fullName ''{1}''")
    @CsvSource({
            "00000000000, Carlos Eduardo do Santos, 201, ",
            "'', Carlos Eduardo do Santos, 400",
            "00000000000, '', 400"
    })
    void testRegisterAccount(ArgumentsAccessor arguments) {
        String document = arguments.get(0).toString();
        String fullName = arguments.get(1).toString();
        Integer statusCode =  Integer.valueOf(arguments.get(2).toString());

        try {
            HttpResponse<?> response = client.toBlocking().exchange(HttpRequest.POST("/accounts",  new AccountRequest(document, fullName)), AccountRequest.class);
            assertEquals(statusCode, response.getStatus().getCode());
        } catch (HttpClientResponseException httpClientResponseException) {
            assertEquals(statusCode, httpClientResponseException.getStatus().getCode());
        }
    }
}