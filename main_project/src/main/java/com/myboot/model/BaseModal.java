package com.myboot.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by majf
 * 2018/8/10 20:39
 */
public class BaseModal implements Serializable {

    private String clubId;
    private Date statDate;

    public String getClubId() {
        return clubId;
    }

    public void setClubId(String clubId) {
        this.clubId = clubId;
    }

    public Date getStatDate() {
        return statDate;
    }

    public void setStatDate(Date statDate) {
        this.statDate = statDate;
    }
}
