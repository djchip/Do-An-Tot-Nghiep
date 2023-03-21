package com.vn.ecommerce.controller;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vn.ecommerce.adapter.HibernateProxyTypeAdapter;
import com.vn.ecommerce.constants.ResponseFontendDefine;
import com.vn.ecommerce.exceptions.RalException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.util.StringUtils;

@Slf4j
public abstract class BaseController {
    GsonBuilder b = new GsonBuilder();

    protected RalException handleException(Exception exception) {

        log.error("Error [{}]", exception.getMessage(), exception);

        String cause = "";
        if (exception.getCause() != null) {
            cause = exception.getCause().getClass().getCanonicalName();
        }

        if (exception instanceof RalException) {
            throw new RalException(HttpStatus.SC_OK, ((RalException) exception).getCode(), exception.getMessage());
        } else if (exception instanceof IllegalArgumentException || cause.contains("IncorrectParameterException")
                || cause.contains("SQLGrammarException")) {
            return new RalException(HttpStatus.SC_INTERNAL_SERVER_ERROR, ResponseFontendDefine.BAD_REQUEST_PARAMS,
                    exception.getMessage());
        } else {
            return new RalException(ResponseFontendDefine.GENERAL, exception.getMessage());
        }

    }

    protected Boolean checkNull(Object obj) {
        return StringUtils.isEmpty(obj);
    }

    protected Gson createGson() {
        b.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
        return b.create();
    }
}
