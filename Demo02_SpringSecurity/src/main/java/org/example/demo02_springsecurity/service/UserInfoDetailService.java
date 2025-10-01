package org.example.demo02_springsecurity.service;

import org.example.demo02_springsecurity.entity.UserInfo;
import org.example.demo02_springsecurity.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserInfoDetailService implements UserDetailsService {

    @Autowired
    private UserInfoRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = repository.findByName(username);
        if (userInfo == null) {
            throw new UsernameNotFoundException("User not found " + username);
        }
        return new UserInfoUserDetails(userInfo);
    }
}
