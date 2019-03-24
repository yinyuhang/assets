package com.hfnu.assets.controller;

import com.hfnu.assets.other.Constants;
import com.hfnu.assets.other.UserService;
import com.hfnu.assets.other.Utils;
import com.hfnu.assets.pojo.Asset;
import com.hfnu.assets.pojo.AssetType;
import com.hfnu.assets.pojo.Borrow;
import com.hfnu.assets.repository.AssetRepository;
import com.hfnu.assets.repository.BorrowRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
public class AssetController {
    @Autowired
    AssetRepository assetRepository;
    @Autowired
    BorrowRepository borrowRepository;
    @Autowired
    UserService userService;

    @GetMapping("/assets")
    Page<Asset> search(int pageIndex, int pageSize
            , @RequestParam(required = false) java.lang.String name
            , @RequestParam(required = false) java.lang.String type
            , @RequestParam(required = false) java.lang.Double price
            , @RequestParam(required = false) Date buyDate
            , @RequestParam(required = false) Date createDate
    ) {
        return this.findByStatus(pageIndex, pageSize, name, type, price, Constants.AVAILABLE, buyDate, createDate);
    }

    @GetMapping("/scraps")
    Page<Asset> search(int pageIndex, int pageSize
            , @RequestParam(required = false) String name
            , @RequestParam(required = false) Double price
            , @RequestParam(required = false) String type
            , @RequestParam(required = false) Date buyDate
            , @RequestParam(required = false) Date createDate
    ) {
        return this.findByStatus(pageIndex, pageSize, name, type, price, Constants.SCRAP, buyDate, createDate);
    }

    private Page<Asset> findByStatus(int pageIndex, int pageSize
            , String name, String type, Double price
            , String status, Date buyDate, Date createDate) {

        Specification<Asset> specification = (Root<Asset> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (null != name && !name.isEmpty()) {
                predicates.add(cb.like(root.get("name"), name));
            }
            if (null != type && !type.isEmpty()) {
                Join<Asset, AssetType> typeJoin = root.join("type", JoinType.LEFT);
                predicates.add(cb.equal(typeJoin.get("name"), type));
            }
            if (null != price) {
                predicates.add(cb.equal(root.get("price"), price));
            }
            if (null != status && !status.isEmpty()) {
                predicates.add(cb.equal(root.get("status"), status));
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
        asset.setStatus(Constants.AVAILABLE);
        asset.setCreateDate(new Date());
        return assetRepository.save(asset);
    }

    @PutMapping("/asset/{id}")
    void update(Asset asset, @PathVariable String id) {
        assetRepository.findById(id).ifPresent(a -> {
            asset.setModifyUser(userService.loadCurrentUser());
            BeanUtils.copyProperties(asset, a, "id", "status", "createUser");
            assetRepository.save(asset);
        });
    }

    @PutMapping("/asset/borrow")
    void borrow(String id) {
        assetRepository.findById(id).ifPresent(asset -> {
            Borrow record = new Borrow(null, Constants.LEND, new Date(), asset, userService.loadCurrentUser(), null);
            asset.setStatus(Constants.LEND);
            assetRepository.save(asset);
            borrowRepository.save(record);
        });
    }

    @PutMapping("/asset/remand")
    void remand(String id) {
        assetRepository.findById(id).ifPresent(asset -> {
            borrowRepository.findByStatusEqualsAndAsset_Id(Constants.LEND, id).ifPresent(record -> {
                record.setStatus(Constants.AVAILABLE);
                record.setModifyUser(userService.loadCurrentUser());
                borrowRepository.save(record);
                asset.setModifyUser(userService.loadCurrentUser());
                asset.setStatus(Constants.AVAILABLE);
                assetRepository.save(asset);
            });
        });
    }

    @PutMapping("/asset/scrap")
    void scrap(String id) {
        assetRepository.findById(id).ifPresent(asset -> {
            asset.setModifyUser(userService.loadCurrentUser());
            asset.setStatus(Constants.SCRAP);
            assetRepository.save(asset);
        });
    }

    @DeleteMapping("/asset/{id}")
    void delete(@PathVariable String id) {
        assetRepository.deleteById(id);
    }
    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(       Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));
    }
}
