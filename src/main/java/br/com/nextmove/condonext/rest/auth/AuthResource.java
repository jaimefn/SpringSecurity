package br.com.nextmove.condonext.rest.auth;

import br.com.nextmove.condonext.dto.userlogin.UserLoginDTO;
import br.com.nextmove.condonext.service.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthResource {

    AuthenticationManager authenticationManager;
    TokenService tokenService;

    @Autowired
    public AuthResource(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @RequestMapping(value = "/auth",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> authenticateUser(@RequestBody @Valid UserLoginDTO userLoginDTO) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = UserLoginDTO.convert(userLoginDTO);
        try{
            Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            return new ResponseEntity<>(tokenService.getToken(authentication), HttpStatus.OK);
        }catch (AuthenticationException ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
