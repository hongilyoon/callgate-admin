package com.hellowd.callgate.component;

import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Slf4j
@NoArgsConstructor
@Component
public class CallGateRestTemplateComponent {

    @Value("${callgate.api.url}")
    private String callGateApiUrl;

    @Value("${callgate.api.key}")
    private String callGateApiKey;

    @Value("${callgate.api.secret}")
    private String callGateApiSecret;

    @Value("${callgate.api.auth}")
    private String callGateApiAuth;

    @Delegate
    RestTemplate restTemplate;
    HttpHeaders headers;

    @PostConstruct
    public void init() {
        // set headers
        this.headers = new HttpHeaders();
        this.headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        this.headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        this.headers.add("x-auth-key", callGateApiAuth);

        // set basic auth
        this.restTemplate = new RestTemplate();
        this.restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(this.callGateApiKey, this.callGateApiSecret));
    }

    public <T, V> ResponseEntity<T> getMember(V request, ParameterizedTypeReference<T> type, String svcCode) {
        return this.invoke(String.format(this.callGateApiUrl, svcCode), HttpMethod.GET, new HttpEntity<>(request, this.headers), type);
    }

    public <T, V> ResponseEntity<T> saveMember(V request, ParameterizedTypeReference<T> type, String svcCode) {
        return this.invoke(String.format(this.callGateApiUrl, svcCode), HttpMethod.POST, new HttpEntity<>(request, this.headers), type);
    }

    public <T> ResponseEntity<T> deleteMember(ParameterizedTypeReference<T> type, String svcCode) {
        return this.invoke(String.format(this.callGateApiUrl, svcCode), HttpMethod.DELETE, new HttpEntity<>(null, this.headers), type);
    }

    private <T> ResponseEntity<T> invoke(String url, HttpMethod httpMethod, HttpEntity<?> httpEntity, ParameterizedTypeReference<T> type) {
        try {
            return this.restTemplate.exchange(url, httpMethod, httpEntity, type);
        } catch (HttpClientErrorException ex) {
            System.out.println(ex.getResponseBodyAsString());
            throw ex;
        }
    }
}
