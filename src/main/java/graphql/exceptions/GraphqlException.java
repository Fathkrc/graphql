package graphql.exceptions;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;

@Component
public class GraphqlException extends DataFetcherExceptionResolverAdapter {
    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        if(ex instanceof UserNotFound){
            return toGraphqlExc(ex);
        }
        return super.resolveToSingleError(ex, env);
    }

    private GraphQLError toGraphqlExc(Throwable throwable){
        return GraphqlErrorBuilder.newError()
                .message(throwable.getMessage())
                .errorType(ErrorType.DataFetchingException)
                .build();
    }
}
