package com.hfnu.assets.controller;

import com.hfnu.assets.dao.AssetRepository;
import com.hfnu.assets.dao.UserRepository;
import com.hfnu.assets.pojo.Asset;
import com.hfnu.assets.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AssetController {
    @Autowired
    AssetRepository assetRepository;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/asset")
    public Asset add (Asset asset, String userId) {
        User user = userRepository.findById(userId).get();
        asset.setCreateUser(user);
        asset.setModifyUser(user);
        return assetRepository.save(asset);
    }
}
