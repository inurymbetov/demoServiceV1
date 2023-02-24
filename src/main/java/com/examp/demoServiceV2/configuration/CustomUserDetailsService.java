package com.examp.demoServiceV2.configuration;

import com.examp.demoServiceV2.dto.UsersDto;
import com.examp.demoServiceV2.service.ServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final ServiceInterface<UsersDto> serviceInterface;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsersDto usersDto = serviceInterface.getByEmail(username);
        if (usersDto == null)
            throw new UsernameNotFoundException("User not found");
        List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("USER"));
        return new User(usersDto.getEmail(), "{noop}" + usersDto.getPassword(), authorities);
    }
}
