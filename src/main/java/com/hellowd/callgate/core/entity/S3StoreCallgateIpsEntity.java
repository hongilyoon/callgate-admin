package com.hellowd.callgate.core.entity;

import com.hellowd.callgate.api.store.http.StoreCallgateIpsData;
import com.hellowd.callgate.core.type.CallGateStatusType;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "s3_store_callgate_ips")
public class S3StoreCallgateIpsEntity {

    private long seq;
    private String prologueUrl;
    private String epilogueUrl;
    private String menuUrl;
    private String useYn;
    private CallGateStatusType status;

    @Id
    @GeneratedValue
    @Column(name = "seq")
    public long getSeq() {
        return seq;
    }

    public void setSeq(long seq) {
        this.seq = seq;
    }

    @Basic
    @Column(name = "prologue_url")
    public String getPrologueUrl() {
        return prologueUrl;
    }

    public void setPrologueUrl(String prologueUrl) {
        this.prologueUrl = prologueUrl;
    }

    @Basic
    @Column(name = "epilogue_url")
    public String getEpilogueUrl() {
        return epilogueUrl;
    }

    public void setEpilogueUrl(String epilogueUrl) {
        this.epilogueUrl = epilogueUrl;
    }

    @Basic
    @Column(name = "menu_url")
    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    @Basic
    @Column(name = "use_yn")
    public String getUseYn() {
        return useYn;
    }

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum")
    public CallGateStatusType getStatus() {
        return status;
    }

    public void setStatus(CallGateStatusType status) {
        this.status = status;
    }

    @Access(AccessType.FIELD)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_seq", referencedColumnName = "seq")
    private S3StoreEntity store;

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    /**
     * Url정보를 셋팅합니다.
     * @param storeCallgateIpsData 설정정보
     */
    public void setData(StoreCallgateIpsData storeCallgateIpsData) {
        this.setPrologueUrl(storeCallgateIpsData.getPrologueUrl());
        this.setEpilogueUrl(storeCallgateIpsData.getEpilogueUrl());
    }
}
