package com.easyShopping.easyShopping.service;

import com.easyShopping.easyShopping.model.Role;
import com.easyShopping.easyShopping.model.User;
import com.easyShopping.easyShopping.repository.UserRepository;
import com.easyShopping.easyShopping.service.Exception.UserServiceException;
import com.easyShopping.easyShopping.service.Exception.UserServiceExceptionCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> appUserOptional = userRepository.findByEmail(email);
        User user = appUserOptional
                .orElseThrow(() -> new UserServiceException(UserServiceExceptionCode.USER_NOT_FOUND));
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        Role role=user.getRole();
        grantedAuthorities.add(new SimpleGrantedAuthority(role.toString()));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
