package com.hfnu.assets.controller;

import com.hfnu.assets.dao.UserRepository;
import com.hfnu.assets.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/users/search")
    Page<User> queryByName(int pageIndex, int size,
                           @RequestParam(required = false) String name,
                           @RequestParam(required = false) String department) {
        return userRepository.findByNameLikeAndDepartment(name, department, new QPageRequest(pageIndex, size));
    }

    @GetMapping("/user/name")
    List<User> queryByName(String name) {
        Specification<User> specification = (Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            //Attempt to sort by Surname, has no effect
            query.orderBy(cb.asc(root.get("surname")));


            //...snip...

            Predicate[] array = predicates.toArray(new Predicate[predicates.size()]);
            return cb.and(array);
        };
        return null;
    }

    @GetMapping("/users")
    Page<User> query(int pageIndex, int size) {
        return userRepository.findAll(new QPageRequest(pageIndex, size));
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
