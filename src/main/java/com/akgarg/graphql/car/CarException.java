package com.akgarg.graphql.car;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.List;

/**
 * @author Akhilesh Garg
 * @since 26-03-2023
 */
public class CarException extends RuntimeException implements GraphQLError {

    public CarException(final String message, final Throwable throwable) {
        super(message, throwable);
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorClassification getErrorType() {
        return null;
    }

}
