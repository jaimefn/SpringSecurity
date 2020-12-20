package br.com.nextmove.condonext.dto.userlogin;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class UserLoginDTO {
    private String login;
    private String password;

    public static UsernamePasswordAuthenticationToken convert(UserLoginDTO userLoginDTO) {
        return new UsernamePasswordAuthenticationToken(userLoginDTO.getLogin(),userLoginDTO.getPassword());
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
