package com.hfnu.assets.controller;

import com.hfnu.assets.repository.LogRepository;
import com.hfnu.assets.pojo.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;


@RestController
public class LogController {
    @Autowired
    LogRepository logRepository;

    @GetMapping("/logs")
    Page<Log> search(int pageIndex, int pageSize
                            ) {
        Specification<Log> specification = (Root<Log> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
        return logRepository.findAll(specification, new QPageRequest(pageIndex, pageSize));
    }
}
