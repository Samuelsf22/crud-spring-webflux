package com.crud.crud_spring_webflux.exception.domain;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

@Component
public class CustomAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = new HashMap<>();
        Throwable throwable = getError(request);

        if (throwable instanceof CustomException) {
            CustomException customException = (CustomException) throwable;
            errorAttributes.put("message", customException.getMessage());
            errorAttributes.put("status", customException.getHttpStatus().value());
        } else {
            errorAttributes.put("message", throwable.getMessage());
            errorAttributes.put("status", 500);
            
        }

        return errorAttributes;
    }

}
