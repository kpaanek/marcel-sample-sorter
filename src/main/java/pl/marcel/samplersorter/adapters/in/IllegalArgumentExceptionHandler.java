package pl.marcel.samplersorter.adapters.in;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.hateoas.JsonError;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;

@Produces
@Singleton
class IllegalArgumentExceptionHandler implements ExceptionHandler<IllegalArgumentException, HttpResponse<JsonError>> {

    @Override
    public HttpResponse<JsonError> handle(HttpRequest request, IllegalArgumentException exception) {
        return HttpResponse.serverError(new JsonError(exception.getMessage())).status(HttpStatus.BAD_REQUEST);
    }
}
