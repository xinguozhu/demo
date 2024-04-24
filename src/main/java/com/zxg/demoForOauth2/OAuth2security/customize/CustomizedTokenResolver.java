package com.zxg.demoForOauth2.OAuth2security.customize;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.server.resource.BearerTokenError;
import org.springframework.security.oauth2.server.resource.BearerTokenErrors;
import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;
import org.springframework.stereotype.Component;

/**
 * 自定义token解析器可以解析url上的access_token
 */
public class CustomizedTokenResolver implements BearerTokenResolver {
    @Override
    public String resolve(HttpServletRequest request) {
        final String parameterToken =  resolveFromRequestParameters(request);

        return parameterToken;
    }

    private String resolveFromRequestParameters(HttpServletRequest request) {
        String token = request.getParameter("access_token");
        return token;
    }
    }

