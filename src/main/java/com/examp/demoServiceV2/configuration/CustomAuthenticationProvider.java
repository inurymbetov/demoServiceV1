//package com.examp.demoServiceV2.configuration;
//
//import com.examp.demoServiceV2.dao.user.UsersImpl;
//import com.examp.demoServiceV2.dto.UsersDto;
//import com.examp.demoServiceV2.dto.UsersRolesDto;
//import com.examp.demoServiceV2.entity.users.Users;
//import com.examp.demoServiceV2.service.ServiceInterface;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class CustomAuthenticationProvider implements AuthenticationProvider {
//
//    @Autowired
//    private ServiceInterface<UsersDto> serviceInterface;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String email = authentication.getName();
//        String password = authentication.getCredentials().toString();
//
//        UsersDto usersDto = serviceInterface.getByEmail(email);
//
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority())
//
//        return null;
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return false;
//    }
//}
