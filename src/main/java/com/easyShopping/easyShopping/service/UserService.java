package com.easyShopping.easyShopping.service;

import com.easyShopping.easyShopping.Mapper.Mapper;
import com.easyShopping.easyShopping.service.Exception.UserServiceException;
import com.easyShopping.easyShopping.service.Exception.UserServiceExceptionCode;
import com.easyShopping.easyShopping.dto.UserDto;
import com.easyShopping.easyShopping.model.User;
import com.easyShopping.easyShopping.repository.UserRepository;
import com.easyShopping.easyShopping.validators.EmailValidator;
import net.bytebuddy.implementation.bytecode.Throw;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.easyShopping.easyShopping.model.Role.ROLE_USER;


@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;
    private UserDetailServiceImpl userDetailService;
    private Mapper mapper;
    private EmailValidator emailValidator;

    @Autowired
    public UserService(UserRepository userRepository, Mapper mapper, UserDetailServiceImpl userDetailService, EmailValidator emailValidator) {

        this.userRepository = userRepository;
        this.userDetailService = userDetailService;
        this.mapper = mapper;
        this.emailValidator=emailValidator;
    }

    public UserDto login(String email, String password) {
        log.info("login: email={}", email);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Optional<User> appUserOptional = userRepository.findByEmail(email);
        User userResult = appUserOptional
                .orElseThrow(() -> new UserServiceException(UserServiceExceptionCode.USER_NOT_FOUND));

        UserDetails userDetails = userDetailService.loadUserByUsername(userResult.getEmail());
        if (!encoder.matches(password, userResult.getPassword())) {
            throw new UserServiceException(UserServiceExceptionCode.BAD_CREDENTIALS);
        }
        Authentication auth =
                new UsernamePasswordAuthenticationToken(userResult, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
        UserDto userDto = mapper.userToDto(userResult);
        log.info("login: result={}", userDto);
        return userDto;
    }

    public UserDto register(UserDto userDto) {
        log.info("register: input={}", userDto);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User appUser = mapper.userDtoToEntity(userDto);
        Optional<User> appUserOptional = userRepository.findByEmail(appUser.getEmail());
        if (!appUserOptional.equals(Optional.empty()))
            throw new UserServiceException(UserServiceExceptionCode.USER_WITH_EMAIL_EXIST);

        if(emailValidator.isValidEmailAddress(userDto.getEmail())==false)
        {
            throw new UserServiceException(UserServiceExceptionCode.EMAIL_INVALID);
        }
        appUser.setPassword(encoder.encode(userDto.getPassword()));
        appUser.setRole(ROLE_USER);
        User appUserResult = userRepository.save(appUser);

        UserDto appUserDtoResult = mapper.userToDto(appUserResult);
        log.info("register: result={}", appUserDtoResult);
        return appUserDtoResult;
    }


}