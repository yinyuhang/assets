package com.hfnu.assets.controller;

import com.hfnu.assets.repository.BorrowRepository;
import com.hfnu.assets.pojo.Borrow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;


@RestController
public class BorrowController {
    @Autowired
    BorrowRepository borrowRepository;

    @GetMapping("/borrows")
    Page<Borrow> search(int pageIndex, int pageSize
                                , @RequestParam(required = false) java.lang.String status
                            ) {
        Specification<Borrow> specification = (Root<Borrow> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (null != status && !status.isEmpty()) {
                predicates.add(cb.equal(root.get("status"), status));
            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
        return borrowRepository.findAll(specification, new QPageRequest(pageIndex, pageSize));
    }

}
