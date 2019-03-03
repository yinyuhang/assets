package com.hfnu.assets.repository;

import com.hfnu.assets.pojo.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AssetRepository extends JpaRepository<Asset, String>, JpaSpecificationExecutor<Asset> {
}