package com.bookcloudpub.auth_server;

import com.bookcloudpub.adminrestserver.domain.User;
import com.bookcloudpub.adminrestserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetailsImpl userDetails = new UserDetailsImpl();
        User user = userRepository.findOne(username);
        userDetails.setAccountNonExpired(true);
        userDetails.setAccountNonLocked(true);
        userDetails.setCredentialsNonExpired(true);
        userDetails.setEnabled(user.isEnabled());
        userDetails.setUsername(username);
        userDetails.setPassword(user.getPassword());
        userDetails.setAuthorities(AuthorityUtils.createAuthorityList(Arrays.toString(user.getUserRoles().toArray())));
        return userDetails;
    }
}
