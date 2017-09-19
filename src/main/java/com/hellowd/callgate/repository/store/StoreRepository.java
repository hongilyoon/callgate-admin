package com.hellowd.callgate.repository.store;

import com.hellowd.callgate.core.entity.S3StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<S3StoreEntity, Long> {
}
