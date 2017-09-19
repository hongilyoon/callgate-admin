package com.hellowd.callgate.service.callgate;

import com.hellowd.callgate.component.CallGateRestTemplateComponent;
import com.hellowd.callgate.core.entity.S3StoreCallgateIpsEntity;
import com.hellowd.callgate.core.type.CallGateStatusType;
import com.hellowd.callgate.core.util.callgateUtil.CallGateHttpRequest;
import com.hellowd.callgate.core.util.callgateUtil.CallGateHttpResponse;
import com.hellowd.callgate.repository.store.StoreCallGateIpsRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Slf4j
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Service
public class CallGateService {

    StoreCallGateIpsRepository storeCallGateIpsRepository;
    CallGateRestTemplateComponent callGateRestTemplateComponent;

    /**
     * CallGate와 연동 하기
     * @param storeSeq 가맹점 PK Seq
     */
    public void saveCallGate(Long storeSeq) {
        // 연동 정보 조회
        S3StoreCallgateIpsEntity s3StoreCallgateIpsEntity = storeCallGateIpsRepository.findOneByStoreSeq(storeSeq);
        if (s3StoreCallgateIpsEntity == null || !StringUtils.hasLength(s3StoreCallgateIpsEntity.getStore().getVtelnum())) {
            throw new RuntimeException(""); // Exception Code는 향후 정리예정
        }

        CallGateStatusType callGateStatusType = CallGateStatusType.FAIL;
        try {
            // 연동끊기
            ResponseEntity<CallGateHttpResponse> response = callGateRestTemplateComponent.saveMember(
                    new CallGateHttpRequest(s3StoreCallgateIpsEntity),
                    new ParameterizedTypeReference<CallGateHttpResponse>() {
                    },
                    s3StoreCallgateIpsEntity.getStore().getVtelnum().replace("-", ""));

            // 성공시 코드셋팅
            if (response.getStatusCode().is2xxSuccessful()) {
                callGateStatusType = CallGateStatusType.SUCCESS;
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);// Exception Code는 향후 정리예정
        } finally {
            // 연동 결과 저장
            s3StoreCallgateIpsEntity.setStatus(callGateStatusType);
            storeCallGateIpsRepository.save(s3StoreCallgateIpsEntity);
        }
    }

    /**
     * CallGate와 연동 끊기
     * @param storeSeq 가맹점 PK Seq
     */
    public void deleteCallGate(Long storeSeq) {
        S3StoreCallgateIpsEntity s3StoreCallgateIpsEntity = storeCallGateIpsRepository.findOneByStoreSeq(storeSeq);
        if (s3StoreCallgateIpsEntity == null || !StringUtils.hasLength(s3StoreCallgateIpsEntity.getStore().getVtelnum())) {
            throw new RuntimeException(""); // Exception Code는 향후 정리예정
        }

        CallGateStatusType callGateStatusType = CallGateStatusType.FAIL;
        try {

            // 연동 끊기
            ResponseEntity<CallGateHttpResponse> response = callGateRestTemplateComponent.deleteMember(
                    new ParameterizedTypeReference<CallGateHttpResponse>() {
                    },
                    s3StoreCallgateIpsEntity.getStore().getVtelnum().replace("-", ""));

            // 성공시 코드셋팅
            if (response.getStatusCode().is2xxSuccessful()) {
                callGateStatusType = CallGateStatusType.NONE;
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);// Exception Code는 향후 정리예정
        } finally {
            // 연동 결과 저장
            s3StoreCallgateIpsEntity.setStatus(callGateStatusType);
            storeCallGateIpsRepository.save(s3StoreCallgateIpsEntity);
        }
    }

}
