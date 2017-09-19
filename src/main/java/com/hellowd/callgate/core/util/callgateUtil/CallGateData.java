package com.hellowd.callgate.core.util.callgateUtil;

import com.hellowd.callgate.core.entity.S3StoreCallgateIpsEntity;
import lombok.Data;

@Data
public class CallGateData {
    private String prologue_url;
    private String menu_url;
    private String epilogue_url;
    private String use_yn;

    public CallGateData(S3StoreCallgateIpsEntity s3StoreCallgateIpsEntity) {
        this.prologue_url = s3StoreCallgateIpsEntity.getPrologueUrl();
        this.menu_url = s3StoreCallgateIpsEntity.getMenuUrl();
        this.epilogue_url = s3StoreCallgateIpsEntity.getEpilogueUrl();
        this.use_yn = s3StoreCallgateIpsEntity.getUseYn();
    }
}
