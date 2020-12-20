package br.com.nextmove.condonext.config.security;

import br.com.nextmove.condonext.domain.userlogin.UserLogin;
import br.com.nextmove.condonext.repository.userlogin.UserLoginRepository;
import br.com.nextmove.condonext.service.security.TokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthTokenFilter extends OncePerRequestFilter {
    private final String AUTHENTICATION_TYPE="Bearer ";

    TokenService tokenService;
    UserLoginRepository userLoginRepository;
    public AuthTokenFilter(TokenService tokenService, UserLoginRepository userLoginRepository){
        this.tokenService = tokenService;
        this.userLoginRepository = userLoginRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain filterChain) throws ServletException, IOException {
        String token = extractTokenFromRequest(req);
        Boolean isValidToken = tokenService.isValidToken(token);
        if(isValidToken){
            authenticateClient(token);
        }
        filterChain.doFilter(req,resp);
    }
    private void authenticateClient(String token){
        Long authId = tokenService.getUserId(token);
        UserLogin userLogin = userLoginRepository.findById(authId).get();
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userLogin,null,userLogin.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }
    private String extractTokenFromRequest(HttpServletRequest req) {
        String authentication = req.getHeader("Authorization");
        if(authentication == null || authentication.isEmpty() || !authentication.contains(AUTHENTICATION_TYPE)) return null;
        return authentication.replace(AUTHENTICATION_TYPE,"");
    }
}
