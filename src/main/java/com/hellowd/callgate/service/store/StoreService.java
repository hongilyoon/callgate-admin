package com.hellowd.callgate.service.store;

import com.hellowd.callgate.api.store.http.StoreCallgateIpsData;
import com.hellowd.callgate.core.entity.S3StoreCallgateIpsEntity;
import com.hellowd.callgate.core.entity.S3StoreEntity;
import com.hellowd.callgate.repository.store.StoreCallGateIpsRepository;
import com.hellowd.callgate.repository.store.StoreRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Service
public class StoreService {

    StoreRepository storeRepository;
    StoreCallGateIpsRepository storeCallGateIpsRepository;

    public Page<S3StoreEntity> findStoreAll(Pageable pageable) {
        Page<S3StoreEntity> result = this.storeRepository.findAll(pageable);
        return result;
    }

    public void saveStoreCallGateIps(StoreCallgateIpsData storeCallgateIpsData) {

        // 조회
        S3StoreCallgateIpsEntity s3StoreCallgateIpsEntity = storeCallGateIpsRepository.findOneByStoreSeq(storeCallgateIpsData.getStoreSeq());
        if (s3StoreCallgateIpsEntity == null || s3StoreCallgateIpsEntity.getStore() == null) {
            s3StoreCallgateIpsEntity = new S3StoreCallgateIpsEntity();
            S3StoreEntity s3StoreEntity = new S3StoreEntity();
            s3StoreEntity.setSeq(storeCallgateIpsData.getStoreSeq());
            s3StoreCallgateIpsEntity.setStore(s3StoreEntity);
        }

        // 수정
        s3StoreCallgateIpsEntity.setData(storeCallgateIpsData);
        storeCallGateIpsRepository.save(s3StoreCallgateIpsEntity);
    }

}
