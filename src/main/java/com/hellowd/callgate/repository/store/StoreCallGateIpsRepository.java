package com.hellowd.callgate.repository.store;

import com.hellowd.callgate.core.entity.S3StoreCallgateIpsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreCallGateIpsRepository extends JpaRepository<S3StoreCallgateIpsEntity, Long> {

    S3StoreCallgateIpsEntity findOneByStoreSeq(@Param("storeSeq") Long storeSeq);
}
