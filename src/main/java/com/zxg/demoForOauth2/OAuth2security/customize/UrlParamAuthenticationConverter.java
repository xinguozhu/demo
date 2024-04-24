package com.zxg.demoForOauth2.OAuth2security.customize;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientAuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class UrlParamAuthenticationConverter implements AuthenticationConverter {
    @Override
    public Authentication convert(HttpServletRequest request) {
        MultiValueMap<String, String> parameters = getParams(request);

        // client_id (REQUIRED)
        String clientId = parameters.getFirst(OAuth2ParameterNames.CLIENT_ID);
        if (!StringUtils.hasText(clientId)) {
            return null;
        }

        if (parameters.get(OAuth2ParameterNames.CLIENT_ID).size() != 1) {
            throw new OAuth2AuthenticationException(OAuth2ErrorCodes.INVALID_REQUEST);
        }

        // client_secret (REQUIRED)
        String clientSecret = parameters.getFirst(OAuth2ParameterNames.CLIENT_SECRET);
        if (!StringUtils.hasText(clientSecret)) {
            return null;
        }

        if (parameters.get(OAuth2ParameterNames.CLIENT_SECRET).size() != 1) {
            throw new OAuth2AuthenticationException(OAuth2ErrorCodes.INVALID_REQUEST);
        }


        Map<String, Object> additionalParameters = getAdditionalParams(request,
                OAuth2ParameterNames.CLIENT_ID,
                OAuth2ParameterNames.CLIENT_SECRET);

        return new OAuth2ClientAuthenticationToken(clientId, ClientAuthenticationMethod.CLIENT_SECRET_POST, clientSecret,
                additionalParameters);
    }

    private MultiValueMap<String, String> getParams(HttpServletRequest request){
        Map<String, String[]> parameterMap = request.getParameterMap();
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameterMap.forEach((key, values) -> {
            // If not query parameter then it's a form parameter

                for (String value : values) {
                    parameters.add(key, value);
                }

        });
        return parameters;
    }

    private Map<String, Object> getAdditionalParams(HttpServletRequest request,String... exclusions){

        MultiValueMap<String, String> multiValueParameters =getParams(request);

        for (String exclusion : exclusions) {
            multiValueParameters.remove(exclusion);
        }

        Map<String, Object> parameters = new HashMap<>();
        multiValueParameters.forEach((key, value) ->
                parameters.put(key, (value.size() == 1) ? value.get(0) : value.toArray(new String[0])));

        return parameters;
    }
}
