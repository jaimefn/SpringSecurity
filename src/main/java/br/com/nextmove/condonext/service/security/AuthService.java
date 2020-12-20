package br.com.nextmove.condonext.service.security;

import br.com.nextmove.condonext.domain.userlogin.UserLogin;
import br.com.nextmove.condonext.repository.userlogin.UserLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {

    UserLoginRepository userLoginRepository;

    @Autowired
    public AuthService(UserLoginRepository userLoginRepository){
        this.userLoginRepository = userLoginRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<UserLogin> userLogin = userLoginRepository.findFirstByLoginAndUserDeletedFalse(login);
        if(userLogin.isPresent()){
            return userLogin.get();
        }
        throw new UsernameNotFoundException("User not found!");
    }
}
