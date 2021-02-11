package br.com.dn.mg.account.application;

import br.com.dn.mg.account.application.payload.AccountDTO;
import br.com.dn.mg.account.application.payload.DepositAccountDTO;
import br.com.dn.mg.account.application.payload.NewAccountDTO;
import br.com.dn.mg.account.application.payload.TransferAccountDTO;
import br.com.dn.mg.account.infrastructure.AccountEntity;
import br.com.dn.mg.account.infrastructure.AccountRepository;
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

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class AccountsResourceTest {
    @Inject
    @Client("/")
    HttpClient client;

    @Inject
    AccountRepository accountRepository;

    @DisplayName("should register a new account. cases: ")
    @ParameterizedTest(name = "{0}")
    @CsvSource({
            "register with success, 83769507037, Carlos Eduardo do Santos, 201",
            "fail when document is invalid, 00000000000, Carlos Eduardo do Santos, 422",
            "account already exist, 83769507037, Carlos Eduardo do Santos, 409",
            "document not found, '', Carlos Eduardo do Santos, 400",
            "name not found, 83769507037, '', 400"
    })
    void testRegisterAccount(ArgumentsAccessor arguments) {
        String document = arguments.get(1).toString();
        String fullName = arguments.get(2).toString();
        Integer statusCode =  Integer.valueOf(arguments.get(3).toString());

        try {
            HttpResponse<?> response = client.toBlocking().exchange(HttpRequest.POST("/accounts",  new NewAccountDTO(document, fullName)), NewAccountDTO.class);
            assertEquals(statusCode, response.getStatus().getCode());
        } catch (HttpClientResponseException httpClientResponseException) {
            assertEquals(statusCode, httpClientResponseException.getStatus().getCode());
        }
    }

    @DisplayName("should deposit the value a account existing. cases: ")
    @ParameterizedTest(name = "{0}")
    @CsvSource({
            "deposit with success, 2000, true, 204",
            "not deposit with negative value, -10000, true, 400",
            "not deposit value more 2000, 2001, true, 400",
            "account not found, 2000, false, 404",
    })
    void testDepositInAccount(ArgumentsAccessor arguments) {
        Double amount = Double.valueOf(arguments.get(1).toString());
        Boolean accountExist =  Boolean.valueOf(arguments.get(2).toString());
        Integer statusCode =  Integer.valueOf(arguments.get(3).toString());

        try {
            UUID accountId = UUID.randomUUID();

            if (accountExist) {
                var account = accountRepository.findByDocument("39670899087");
                accountId = account.get().getId();
            }

            HttpResponse<?> response = client.toBlocking().exchange(HttpRequest.PATCH("/accounts/" + accountId.toString() + "/deposit",  new DepositAccountDTO(amount)), DepositAccountDTO.class);
            assertEquals(statusCode, response.getStatus().getCode());
        } catch (HttpClientResponseException httpClientResponseException) {
            assertEquals(statusCode, httpClientResponseException.getStatus().getCode());
        }
    }

    @DisplayName("should get the account registered. cases: ")
    @ParameterizedTest(name = "{0}")
    @CsvSource({
            "return the account registered, true, 200",
            "return 404 to account not found, false, 404",
    })
    void testGetAccount(ArgumentsAccessor arguments) {
        Boolean accountExist =  Boolean.valueOf(arguments.get(1).toString());
        Integer statusCode =  Integer.valueOf(arguments.get(2).toString());

        try {
            UUID accountId = UUID.randomUUID();

            AccountEntity account = new AccountEntity();
            if (accountExist) {
                var  optAccount = accountRepository.findByDocument("39670899087");
                account = optAccount.get();
                accountId = account.getId();
            }

            HttpResponse<?> response = client.toBlocking().exchange(HttpRequest.GET("/accounts/" + accountId.toString()), AccountDTO.class);
            assertEquals(statusCode, response.getStatus().getCode());

            var accountResponse = response.getBody(AccountDTO.class).get();

            assertEquals(account.getFullName(), accountResponse.getFullName());
            assertEquals(account.getDocument(), accountResponse.getDocument());
            assertEquals(account.getAmount(), accountResponse.getBalanceAmount());

        } catch (HttpClientResponseException httpClientResponseException) {
            assertEquals(statusCode, httpClientResponseException.getStatus().getCode());
        }
    }

    @DisplayName("should get the transactions registered. cases: ")
    @ParameterizedTest(name = "{0}")
    @CsvSource({
            "return the transactions registered, true, 200",
            "return 404 to account not found, false, 404",
    })
    void testGetAccountWithTransaction(ArgumentsAccessor arguments) {
        Boolean accountExist =  Boolean.valueOf(arguments.get(1).toString());
        Integer statusCode =  Integer.valueOf(arguments.get(2).toString());

        try {
            UUID accountId = UUID.randomUUID();

            AccountEntity account = new AccountEntity();
            if (accountExist) {
                var  optAccount = accountRepository.findByDocument("39670899087");
                account = optAccount.get();
                accountId = account.getId();
            }

            HttpResponse<?> response = client.toBlocking().exchange(HttpRequest.GET("/accounts/" + accountId.toString()), AccountDTO.class);
            assertEquals(statusCode, response.getStatus().getCode());

            var accountResponse = response.getBody(AccountDTO.class).get();

            assertEquals(account.getFullName(), accountResponse.getFullName());
            assertEquals(account.getDocument(), accountResponse.getDocument());

        } catch (HttpClientResponseException httpClientResponseException) {
            assertEquals(statusCode, httpClientResponseException.getStatus().getCode());
        }
    }

    @DisplayName("should perform transfer entry account. cases: ")
    @ParameterizedTest(name = "{0}")
    @CsvSource({
            "return the transfer perform with success, true, true, 400, 204",
            "return error to account of origen not found, false, true, 50, 404",
            "return error to account of destination not found, true, false, 50, 404",
            "return to account of origin without balance, true, true, 2000, 422",
    })
    void testTransferEntryAccounts(ArgumentsAccessor arguments) {
        Boolean toAccountExist =  Boolean.valueOf(arguments.get(1).toString());
        Boolean fromAccountExist =  Boolean.valueOf(arguments.get(2).toString());

        Double value =  Double.valueOf(arguments.get(3).toString());
        Integer statusCode =  Integer.valueOf(arguments.get(4).toString());

        try {
            UUID toAccountId = UUID.randomUUID();
            UUID fromAccountId = UUID.randomUUID();

            if (toAccountExist) {
                toAccountId = accountRepository.findByDocument("39828082004").get().getId();
            }

            if (fromAccountExist) {
                fromAccountId = accountRepository.findByDocument("37692501092").get().getId();
            }

            HttpResponse<?> response = client.toBlocking().exchange(HttpRequest.PATCH("/accounts/" + toAccountId.toString() + "/transfer",  new TransferAccountDTO(fromAccountId, value)), TransferAccountDTO.class);
            assertEquals(statusCode, response.getStatus().getCode());
        } catch (HttpClientResponseException httpClientResponseException) {
            assertEquals(statusCode, httpClientResponseException.getStatus().getCode());
        }
    }
}