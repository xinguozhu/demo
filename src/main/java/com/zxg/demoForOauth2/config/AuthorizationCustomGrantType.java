package com.zxg.demoForOauth2.config;

import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.util.Assert;

import java.io.Serializable;

public record AuthorizationCustomGrantType(String value) implements Serializable {


    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    public static final AuthorizationGrantType PASSWORD_CREDENTIALS = new AuthorizationGrantType("password");

    /**
     * @deprecated The latest OAuth 2.0 Security Best Current Practice disallows the use
     * of the Resource Owner Password Credentials grant. See reference
     * <a target="_blank" href=
     * "https://datatracker.ietf.org/doc/html/draft-ietf-oauth-security-topics-19#section-2.4">OAuth
     * 2.0 Security Best Current Practice.</a>
     */
    @Deprecated
    public static final AuthorizationGrantType PASSWORD = new AuthorizationGrantType("password");

    /**
     * @since 5.5
     */
    public static final AuthorizationGrantType JWT_BEARER = new AuthorizationGrantType(
            "urn:ietf:params:oauth:grant-type:jwt-bearer");

    /**
     * @since 6.1
     */
    public static final AuthorizationGrantType DEVICE_CODE = new AuthorizationGrantType(
            "urn:ietf:params:oauth:grant-type:device_code");

    /**
     * Constructs an {@code AuthorizationGrantType} using the provided value.
     *
     * @param value the value of the authorization grant type
     */
//    public void AuthorizationGrantType(String value) {
//        Assert.hasText(value, "value cannot be empty");
//        this.value = value;
//    }

    /**
     * Returns the value of the authorization grant type.
     *
     * @return the value of the authorization grant type
     */
    @Override
    public String value() {
        return this.value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        AuthorizationGrantType that = (AuthorizationGrantType) obj;
        return this.value().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return this.value().hashCode();
    }
}
