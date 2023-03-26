package com.akgarg.graphql.car;

import graphql.ErrorClassification;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import org.springframework.graphql.ResponseError;
import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.graphql.server.WebGraphQlRequest;
import org.springframework.graphql.server.WebGraphQlResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.*;

/**
 * An implementation class for {@link WebGraphQlInterceptor} which is used to handle the errors related to bad queries including invalid syntax or validation related errors which are not propagated to {@link CarExceptionHandler} exception handler.
 *
 * @author Akhilesh Garg
 * @since 26-03-2023
 */
@Component
public class CarResponseInterceptor implements WebGraphQlInterceptor {

    @Override
    public Mono<WebGraphQlResponse> intercept(final WebGraphQlRequest request, final Chain chain) {
        return chain.next(request).map(response -> {
            if (response.getErrors().isEmpty()) {
                return response;
            }

            final List<GraphQLError> errors = getErrorList(response.getErrors());
            return response.transform(builder -> builder.errors(errors));
        });
    }

    private List<GraphQLError> getErrorList(final List<ResponseError> errors) {
        final List<GraphQLError> _errors = new ArrayList<>();

        for (final ResponseError error : errors) {
            _errors.add(new CustomGraphqlError(parseErrorMessage(error.getMessage()), error.getErrorType()));
        }

        return _errors;
    }

    private String parseErrorMessage(final String errorMessage) {
        try {
            final String fieldName = errorMessage.substring(
                    errorMessage.indexOf("@[") + 2,
                    errorMessage.indexOf("]")
            );
            final String message = errorMessage.substring(errorMessage.lastIndexOf(":") + 1).trim();
            return message + " in " + fieldName;
        } catch (Throwable e) {
            return errorMessage;
        }
    }

    @SuppressWarnings("ClassCanBeRecord")
    private static class CustomGraphqlError implements GraphQLError {

        private final String message;
        private final ErrorClassification errorType;

        CustomGraphqlError(
                final String message,
                final ErrorClassification errorType
        ) {
            this.message = message;
            this.errorType = errorType;
        }

        @Override
        public String getMessage() {
            return this.message;
        }

        @Override
        public List<SourceLocation> getLocations() {
            return Collections.emptyList();
        }

        @Override
        public ErrorClassification getErrorType() {
            return this.errorType;
        }

        @Override
        public Map<String, Object> toSpecification() {
            final HashMap<String, Object> specifications = new HashMap<>();
            specifications.put("error_message", message);
            specifications.put("error_code", statusCode());
            return specifications;
        }

        private int statusCode() {
            // todo: make this method more robust but for demonstration, it is okay
            if (ErrorType.ValidationError.equals(errorType) ||
                    ErrorType.InvalidSyntax.equals(errorType) ||
                    ErrorType.DataFetchingException.equals(errorType)) {
                return 400;
            }

            return 500;
        }
    }

}
