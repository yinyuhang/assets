package com.hfnu.assets.other;

import com.hfnu.assets.pojo.User;
import com.hfnu.assets.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;

//@Service
public class DataInit {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @PostConstruct
    void dataInit() {
        User admin = new User();
        admin.setName("admin");
        admin.setPwd(passwordEncoder.encode("admin"));
        admin.setCreateDate(new Date());
        admin.setRole(Constants.ROLE.ADMIN.toString());
        userRepository.save(admin);
    }
}