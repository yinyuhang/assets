package com.hfnu.assets.dao;

import com.hfnu.assets.pojo.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRepository extends JpaRepository<Asset, String> {
}
