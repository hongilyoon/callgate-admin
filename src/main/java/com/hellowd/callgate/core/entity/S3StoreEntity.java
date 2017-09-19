package com.hellowd.callgate.core.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "s3_store", schema = "s3_testbed", catalog = "")
public class S3StoreEntity {
    private long seq;
    private String name;
    private String telnum;
    private String vtelnum;

    @Id
    @Column(name = "seq")
    @GeneratedValue
    public long getSeq() {
        return seq;
    }

    public void setSeq(long seq) {
        this.seq = seq;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "telnum")
    public String getTelnum() {
        return telnum;
    }

    public void setTelnum(String telnum) {
        this.telnum = telnum;
    }

    @Basic
    @Column(name = "vtelnum")
    public String getVtelnum() {
        return vtelnum;
    }

    public void setVtelnum(String vtelnum) {
        this.vtelnum = vtelnum;
    }

    @Access(AccessType.FIELD)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_seq", referencedColumnName = "seq")
    private S3StoreOwnerEntity storeOwner;

    @Access(AccessType.FIELD)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "store")
    private List<S3StoreCallgateIpsEntity> storeCallgateIpses;

    @Transient
    public S3StoreCallgateIpsEntity getStoreCallgateIps() {
        if (this.storeCallgateIpses != null && this.storeCallgateIpses.size() > 0) {
            return this.storeCallgateIpses.get(0);
        }

        return null;
    }
}
