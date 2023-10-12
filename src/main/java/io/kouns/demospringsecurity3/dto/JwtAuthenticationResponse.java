package io.kouns.demospringsecurity3.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;

public class JwtAuthenticationResponse {
    private String token;
    private String refreshToken;
    @Temporal(value = TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private Date expiryToken;
    private String tokenPermission;

    public JwtAuthenticationResponse(String token, String refreshToken, Date expiryToken, String tokenPermission) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.expiryToken = expiryToken;
        this.tokenPermission = tokenPermission;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Date getExpiryToken() {
        return expiryToken;
    }

    public void setExpiryToken(Date expiryToken) {
        this.expiryToken = expiryToken;
    }

    public String getTokenPermission() {
        return tokenPermission;
    }

    public void setTokenPermission(String tokenPermission) {
        this.tokenPermission = tokenPermission;
    }
}
