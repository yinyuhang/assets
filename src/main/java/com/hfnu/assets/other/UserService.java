package com.hfnu.assets.other;

import com.hfnu.assets.pojo.User;
import com.hfnu.assets.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = loadUserByName(username);
        if (!optionalUser.isPresent())
            throw new UsernameNotFoundException("User not found " + username);
        User user = optionalUser.get();
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        return new org.springframework.security.core.userdetails.User(username, user.getPwd(), authorities);
    }

    public Optional<User> loadUserByName(String username) {
        List<User> users = userRepository.findByName(username);
        if (users == null || users.isEmpty())
            return Optional.empty();
        return Optional.of(users.get(0));
    }

    public User loadCurrentUser() {
        User user = (User) ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes())
                .getRequest().getSession().getAttribute("user");
        if (user == null) {
            return null;
        }
        return user;
    }
}

