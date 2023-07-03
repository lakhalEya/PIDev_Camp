package tn.camps.tuncamps.commons.security;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import tn.camps.tuncamps.user.repository.UserEntity;
import tn.camps.tuncamps.user.repository.UserRepository;
import tn.camps.tuncamps.user.repository.UserRole;

@Service
@AllArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username);
        return new User(userEntity.getUsername(),userEntity.getPassword(),mapToAuthorities(
                Collections.singletonList(userEntity.getRole())));
    }

    private Collection<GrantedAuthority> mapToAuthorities(List<UserRole> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toList());
    }
}
