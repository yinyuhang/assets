package com.hfnu.assets.repository;

import com.hfnu.assets.pojo.AssetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AssetTypeRepository extends JpaRepository<AssetType, String>, JpaSpecificationExecutor<AssetType> {
}