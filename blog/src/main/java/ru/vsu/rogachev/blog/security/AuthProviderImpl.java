package ru.vsu.rogachev.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import ru.vsu.rogachev.blog.services.PersonDetailsService;

import java.util.Collections;

public class AuthProviderImpl implements AuthenticationProvider {

    private final PersonDetailsService personDetailsService;

    @Autowired
    public AuthProviderImpl(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();

        UserDetails userDetails = personDetailsService.loadUserByUsername(username);

        String password = authentication.getCredentials().toString();

        if (!password.equals(userDetails.getPassword())){
            throw new BadCredentialsException("Incorrect password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, password,
                Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
