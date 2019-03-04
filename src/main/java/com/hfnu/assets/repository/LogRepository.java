package com.hfnu.assets.repository;

import com.hfnu.assets.pojo.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LogRepository extends JpaRepository<Log, String>, JpaSpecificationExecutor<Log> {
}