package com.myboot.model;

import java.util.Date;

public class IncrementUpdateRecord {
    private Long id;

    private String type;

    private String description;

    private String lastRecordId;

    private Date modifyTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getLastRecordId() {
        return lastRecordId;
    }

    public void setLastRecordId(String lastRecordId) {
        this.lastRecordId = lastRecordId == null ? null : lastRecordId.trim();
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}