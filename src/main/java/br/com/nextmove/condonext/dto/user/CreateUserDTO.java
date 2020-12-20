package br.com.nextmove.condonext.dto.user;

import br.com.nextmove.condonext.dto.userlogin.UserLoginDTO;

public class CreateUserDTO extends UserDTO {

    private UserLoginDTO userLogin;

    public UserLoginDTO getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(UserLoginDTO userLogin) {
        this.userLogin = userLogin;
    }
}
