package com.akgarg.graphql.car;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * Exception handler class to handle exceptions which are thrown by {@code GraphQL Engine} while executing business logic
 * <p>All exceptions which will be thrown by business layer will land here</p>
 *
 * @author Akhilesh Garg
 * @since 26-03-2023
 */
@Component
public class CarExceptionHandler extends DataFetcherExceptionResolverAdapter {

    private static final Logger logger = Logger.getLogger(CarExceptionHandler.class.getSimpleName());

    @Override
    protected GraphQLError resolveToSingleError(final Throwable exception, final DataFetchingEnvironment environment) {
        logger.info("[CarExceptionHandler] Exception type: " + exception);

        if (exception instanceof CarException) {
            logger.info("[CarExceptionHandler] CarException type");

            return handleCarException((CarException) exception, environment);
        }

        return handleException(exception, environment);
    }

    private GraphQLError handleException(final Throwable exception, final DataFetchingEnvironment environment) {
        logger.severe("[CarExceptionHandler] handling " + exception.getClass().getSimpleName());
        logger.severe("[CarExceptionHandler] error message: " + exception.getMessage());

        return GraphqlErrorBuilder.newError(environment)
                .message(exception.getMessage())
                .build();
    }

    private GraphQLError handleCarException(
            final CarException exception,
            final DataFetchingEnvironment environment
    ) {
        logger.warning("[CarExceptionHandler] handling CarException...");

        return GraphqlErrorBuilder.newError(environment)
                .message(exception.getMessage()).build();
    }

}
