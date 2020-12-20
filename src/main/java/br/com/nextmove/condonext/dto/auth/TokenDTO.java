package br.com.nextmove.condonext.dto.auth;

public class TokenDTO {
    private String token;
    private String type;
    private Long expiration;

    public TokenDTO(String token, String type, Long jwtExpirationTime) {
        this.token = token;
        this.type = type;
        this.expiration = jwtExpirationTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getExpiration() {
        return expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }
}
