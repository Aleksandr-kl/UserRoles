package com.example.userrole.service;

import com.example.userrole.dao.UserRepository;
import com.example.userrole.dto.UserDTO;
import com.example.userrole.model.Role;
import com.example.userrole.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl() {
        this(null, null);
    }

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    //створення user
    public boolean save(UserDTO userDTO) {
        if(!Objects.equals(userDTO.getPassword(), userDTO.getMatchingPassword()))//Порівняння введених паролей
        {
            throw new RuntimeException("Password is not equals");
        }
        User user= User.builder()
                .name(userDTO.getUserName())
                .password(userDTO.getPassword())
                .email(userDTO.getEmail())
                .role(Role.USER)
                .build();
        userRepository.save(user);
        return true;
    }

    @Override
    //пошук(завантаження) user
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findFirstByName(username);
        if(user==null){
            throw new UsernameNotFoundException("User not found with name: "+username);
        }
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(user.getRole().name()));
        return new org.springframework.security.core.userdetails.User(
                user.getName(),
                user.getPassword(),
                roles
        );
    }
}
