package ru.feodorkek.dev.crazypoint.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.feodorkek.dev.crazypoint.config.properties.WebPrivateAccessProperties;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccessUserDetailsServiceImpl implements UserDetailsService {

    private final WebPrivateAccessProperties accessProperties;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        if (username.equals(accessProperties.getUsername())) {
            return new User(accessProperties.getUsername(),
                    passwordEncoder.encode(accessProperties.getPassword()),
                    List.of(new SimpleGrantedAuthority("ROLE_ADMIN")));
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

}
