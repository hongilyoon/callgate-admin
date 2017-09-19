package com.hellowd.callgate.api.store.http;

import com.hellowd.callgate.core.type.CallGateStatusType;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class StoreCallgateIpsData {
    private Long seq;
    @NotNull
    private Long storeSeq;
    private String prologueUrl;
    private String menuUrl;
    private String epilogueUrl;
    private String useYn;
    private CallGateStatusType status;
}
