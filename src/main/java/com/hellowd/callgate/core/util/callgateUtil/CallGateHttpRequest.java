package com.hellowd.callgate.core.util.callgateUtil;

import com.hellowd.callgate.core.entity.S3StoreCallgateIpsEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CallGateHttpRequest {

    private CallGateData data;

    public CallGateHttpRequest(S3StoreCallgateIpsEntity s3StoreCallgateIpsEntity) {
        this.data = new CallGateData(s3StoreCallgateIpsEntity);
    }
}
