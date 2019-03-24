package com.hfnu.assets.controller;

import com.hfnu.assets.repository.AssetTypeRepository;
import com.hfnu.assets.pojo.AssetType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;


@RestController
public class AssetTypeController {
    @Autowired
    AssetTypeRepository assettypeRepository;

    @GetMapping("/assettypes")
    Page<AssetType> queryByName(int pageIndex, int pageSize
                            ) {
        Specification<AssetType> specification = (Root<AssetType> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
        return assettypeRepository.findAll(specification, new QPageRequest(pageIndex, pageSize));
    }

    @PostMapping("/assettype")
    AssetType add(AssetType assettype) {
        return assettypeRepository.save(assettype);
    }

    @PutMapping("/assettype/{id}")
    AssetType update(AssetType assettype, @PathVariable String id) {
        assettype.setId(id);
        return assettypeRepository.save(assettype);
    }

    @DeleteMapping("/assettype/{id}")
    void delete(@PathVariable String id) {
        assettypeRepository.deleteById(id);
    }

}
