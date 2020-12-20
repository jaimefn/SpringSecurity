package br.com.nextmove.condonext.dto.auth;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class AuthDTO {
    private String login;
    private String password;

    public static UsernamePasswordAuthenticationToken convert(AuthDTO authDTO) {
        return new UsernamePasswordAuthenticationToken(authDTO.getLogin(),authDTO.getPassword());
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
