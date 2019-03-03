package com.hfnu.assets.controller;

import com.hfnu.assets.repository.AssetRepository;
import com.hfnu.assets.pojo.Asset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;


@RestController
public class AssetController {
    @Autowired
    AssetRepository assetRepository;

    @GetMapping("/assets")
    Page<Asset> queryByName(int pageIndex, int pageSize
                                , @RequestParam(required = false) java.lang.String name
                                , @RequestParam(required = false) java.lang.String type
                                , @RequestParam(required = false) java.lang.Double price
                                , @RequestParam(required = false) java.util.Date buyDate
                                , @RequestParam(required = false) java.util.Date createDate
                            ) {
        Specification<Asset> specification = (Root<Asset> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (null != name && !name.isEmpty()) {
                predicates.add(cb.like(root.get("name"), name));
            }
            if (null != type && !type.isEmpty()) {
                predicates.add(cb.equal(root.get("type"), type));
            }
            if (null != price) {
                predicates.add(cb.equal(root.get("price"), price));
            }
            if (null != buyDate) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("buyDate"), buyDate));
            }
            if (null != createDate) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("createDate"), createDate));
            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
        return assetRepository.findAll(specification, new QPageRequest(pageIndex, pageSize));
    }

    @PostMapping("/asset")
    Asset add(Asset asset) {
        return assetRepository.save(asset);
    }

    @PutMapping("/asset/{id}")
    Asset update(Asset asset, @PathVariable String id) {
        asset.setId(id);
        return assetRepository.save(asset);
    }

    @DeleteMapping("/asset/{id}")
    void delete(@PathVariable String id) {
        assetRepository.deleteById(id);
    }

}
