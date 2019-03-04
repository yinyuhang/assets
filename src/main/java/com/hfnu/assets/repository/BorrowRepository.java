package com.hfnu.assets.repository;

import com.hfnu.assets.pojo.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface BorrowRepository extends JpaRepository<Borrow, String>, JpaSpecificationExecutor<Borrow> {
    Optional<Borrow> findByStatusEqualsAndAsset_Id(String status, String id);
}