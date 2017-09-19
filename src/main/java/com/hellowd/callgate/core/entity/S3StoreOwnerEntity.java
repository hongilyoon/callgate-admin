package com.hellowd.callgate.core.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "s3_store_owner")
public class S3StoreOwnerEntity {
    private long seq;
    private String id;
    private String name;

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
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Access(AccessType.FIELD)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "storeOwner")
    private List<S3StoreEntity> stores;
}
