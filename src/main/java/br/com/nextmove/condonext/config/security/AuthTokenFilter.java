package br.com.nextmove.condonext.config.security;

import br.com.nextmove.condonext.domain.user.User;
import br.com.nextmove.condonext.globalhandler.UserRepository;
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
    UserRepository userRepository;
    public AuthTokenFilter(TokenService tokenService, UserRepository userRepository){
        this.tokenService = tokenService;
        this.userRepository = userRepository;
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
        Long userId = tokenService.getUserId(token);
        User user = userRepository.findById(userId).get();
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }
    private String extractTokenFromRequest(HttpServletRequest req) {
        String autentication = req.getHeader("Authorization");
        if(autentication == null || autentication.isEmpty() || !autentication.contains(AUTHENTICATION_TYPE)) return null;
        return autentication.replace(AUTHENTICATION_TYPE,"");
    }
}
