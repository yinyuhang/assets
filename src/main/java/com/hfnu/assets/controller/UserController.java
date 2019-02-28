package com.hfnu.assets.controller;

import com.hfnu.assets.dao.UserRepository;
import com.hfnu.assets.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    Page<User> queryByName(int pageIndex, int pageSize,
                           @RequestParam(required = false) String name,
                           @RequestParam(required = false) String department) {
        Specification<User> specification = (Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (!StringUtils.isEmpty(name)) {
                Path<String> namePath = root.get("name");
                predicates.add(cb.like(namePath, name));
            }
            if (!StringUtils.isEmpty(department)) {
                Path<String> namePath = root.get("department");
                predicates.add(cb.equal(namePath, department));
            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
        return userRepository.findAll(specification, new QPageRequest(pageIndex, pageSize));
    }

    @PostMapping("/user")
    User add(User user) {
        return userRepository.save(user);
    }

    @PutMapping("/user/{id}")
    User update(User user, @PathVariable String id) {
        user.setId(id);
        return userRepository.save(user);
    }

    @DeleteMapping("/user/{id}")
    void delete(@PathVariable String id) {
        userRepository.deleteById(id);
    }

}
