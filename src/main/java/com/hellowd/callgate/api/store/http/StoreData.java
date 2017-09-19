package com.hellowd.callgate.api.store.http;

import lombok.Data;

@Data
public class StoreData {
    private long seq;
    private String name;
    private String telnum;
    private String vtelnum;
    private StoreOwnerData storeOwner;
    private StoreCallgateIpsData storeCallgateIps;
}
