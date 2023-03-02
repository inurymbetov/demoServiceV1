package com.examp.demoServiceV2.configuration;

import com.examp.demoServiceV2.dao.user.UsersImpl;
import com.examp.demoServiceV2.dto.UsersDto;
import com.examp.demoServiceV2.service.ServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final ServiceInterface<UsersDto> serviceInterface;
    private final UsersImpl users;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsersDto usersDto = serviceInterface.getByEmail(username);
        if (usersDto == null)
            throw new UsernameNotFoundException("User not found");
        List<SimpleGrantedAuthority> authorities = users.getRolesByUser(usersDto.getId()).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return new User(
                usersDto.getEmail(),
                usersDto.getPassword(),
                true,
                true,
                true,
                true,
                authorities
        );
    }
}
