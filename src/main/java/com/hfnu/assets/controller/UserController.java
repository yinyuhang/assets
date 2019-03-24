package com.hfnu.assets.controller;

import com.hfnu.assets.other.Constants;
import com.hfnu.assets.repository.UserRepository;
import com.hfnu.assets.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;

    @GetMapping("/users")
    Page<User> search(int pageIndex, int pageSize
                                , @RequestParam(required = false) java.lang.String name
                                , @RequestParam(required = false) java.lang.String department
                            ) {
        Specification<User> specification = (Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (null != name && !name.isEmpty()) {
                predicates.add(cb.like(root.get("name"), name));
            }
            if (null != department && !department.isEmpty()) {
                predicates.add(cb.equal(root.get("department"), department));
            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
        return userRepository.findAll(specification, new QPageRequest(pageIndex, pageSize));
    }

    @PostMapping("/user")
    void add(User user) {
        user.setRole(Constants.ROLE.USER.toString());
        user.setCreateDate(new Date());
        userRepository.save(user);
    }

    @PutMapping("/user/{id}")
    void update(User user, @PathVariable String id) {
        user.setId(id);
        user.setPwd(encoder.encode(user.getPwd()));
        userRepository.save(user);
    }

    @DeleteMapping("/user/{id}")
    void delete(@PathVariable String id) {
        userRepository.deleteById(id);
    }

}
