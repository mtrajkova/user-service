package com.bachelor.microservice2.configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;

@Component
public class FeignClientRequestInterceptor implements RequestInterceptor {

    private final String apiKeyHeaderName;
    private final String apiKey;

    @Autowired
    public FeignClientRequestInterceptor(@Value("${api-key.name}") String apiKeyHeaderName,
                                         @Value("${api-key.secret}") String apiKey) {
        this.apiKeyHeaderName = apiKeyHeaderName;
        this.apiKey = apiKey;
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        Map<String, Collection<String>> headers = requestTemplate.headers();
        removeApiKeyIfAlreadyExistInHeader(headers);
        requestTemplate.header(apiKeyHeaderName, apiKey);
//        requestTemplate.header(ACTOR_TYPE, CONSUMER_ACTOR_TYPE_VALUE);
    }

    private void removeApiKeyIfAlreadyExistInHeader(Map<String, Collection<String>> headers) {
        if (headers.containsKey(apiKeyHeaderName)) {
            headers.remove(apiKeyHeaderName);
        }
    }
}
