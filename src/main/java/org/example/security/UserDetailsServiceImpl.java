package org.example.security;

import org.example.dto.user.UserResponseDto;
import org.example.model.UserRole;
import org.example.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Component;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserResponseDto userDto = userService.findByName(username);
        if(userDto == null) {
            throw new UsernameNotFoundException("User not found" + username);
        }
        List<SimpleGrantedAuthority> authorities = toList(userDto.getRoles());
        return new User(userDto.getName(), userDto.getPassword(), authorities);
    }

    public List<SimpleGrantedAuthority> toList(Set<UserRole> roles){
        if(roles==null){
            return null;
        }
        List<SimpleGrantedAuthority> list = new ArrayList<>(roles.size());
            for(UserRole role : roles){
                list.add(new SimpleGrantedAuthority(role.getName()));
            }
        return list;
    }
}
