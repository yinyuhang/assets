package com.hfnu.assets.dao;

import com.hfnu.assets.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    Page<User> findByNameLikeAndDepartment(String name, String department, Pageable pageable);
    List<User> findByNameLike (String name);
}
