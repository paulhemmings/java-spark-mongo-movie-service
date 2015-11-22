package com.razor.movies.server.resources;

import com.google.gson.Gson;
import com.razor.movies.server.services.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.exception;

public class BaseResource {
    protected static final Logger LOGGER = LoggerFactory.getLogger(BaseResource.class);

    public BaseResource() {

        exception(ServiceException.class, (e, request, response) -> {

            int errorCode = ((ServiceException)e).getStatusCode();
            String errorMessage = e.getMessage();

            LOGGER.error(errorMessage);
            response.status(errorCode);

            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("errorCode", String.valueOf(errorCode));
            errorMap.put("errorMessage", errorMessage);

            response.body(new Gson().toJson(errorMap));
        });

    }
}
