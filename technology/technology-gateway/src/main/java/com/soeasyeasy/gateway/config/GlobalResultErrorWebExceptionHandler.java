package com.soeasyeasy.gateway.config;

import com.soeasyeasy.common.constants.GlobalResCodeConstants;
import com.soeasyeasy.common.entity.Result;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * Adapt the response content to {@link Result}
 *
 * @author hc
 */
public class GlobalResultErrorWebExceptionHandler extends DefaultErrorWebExceptionHandler {

    /**
     * Create a new {@code DefaultErrorWebExceptionHandler} instance.
     *
     * @param errorAttributes    the error attributes
     * @param resources          the resources configuration properties
     * @param errorProperties    the error configuration properties
     * @param applicationContext the current application context
     * @since 2.4.0
     */
    public GlobalResultErrorWebExceptionHandler(ErrorAttributes errorAttributes,
                                                WebProperties.Resources resources,
                                                ErrorProperties errorProperties,
                                                ApplicationContext applicationContext) {
        super(errorAttributes, resources, errorProperties, applicationContext);
    }

    /**
     * Render the error information as a JSON payload.
     *
     * @param request the current request
     * @return a {@code Publisher} of the HTTP response
     */
    @Override
    protected Mono<ServerResponse> renderErrorResponse(ServerRequest request) {

        Map<String, Object> error = getErrorAttributes(request, getErrorAttributeOptions(request, MediaType.ALL));

        String message = String.format("Error routing to %s due to %s", error.get("path"), error.get("message"));
        Result<Map<String, Object>> result = Result.error(GlobalResCodeConstants.INTERNAL_SERVER_ERROR.getCode(), message);

        return ServerResponse.status(getHttpStatus(error))
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(result));
    }

}
