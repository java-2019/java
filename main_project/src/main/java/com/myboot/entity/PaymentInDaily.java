package com.myboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by majf
 * 2018/7/25 20:25
 */

@Entity
public class PaymentInDaily  {

    @Id
    @GeneratedValue
    private  Integer id;

    private Date date;
    private long clubNum;
    private long totalNum;
    private Double totalFee;
    private long totalAliNum;
    private Double totalAliFee;
    private long totalWxNum;
    private Double totalWxFee;
    private long consumeNum;
    private Double consumeFee;
    private long fastPayNum;
    private Double fastPayFee;
    private long itemCardNum;
    private Double itemCardFee;
    private long itemPackageNum;
    private Double itemPackageFee;
    private long oneYuanNum;
    private Double oneYuanFee;
    private long orderNum;
    private Double orderFee;
    private long paidCouponNum;
    private Double paidCouponFee;
    private long paidOrderNum;
    private Double paidOrderFee;
    private long paidServiceItemNum;
    private Double paidServiceItemFee;
    private long serviceItemNum;
    private Double serviceItemFee;
    private long userRechargeNum;
    private Double userRechargeFee;
    private long userRewardNum;
    private Double userRewardFee;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    public Date getDate() {
        return date;
    }


    public void setDate(Date date) {
        this.date = date;
    }

    public long getClubNum() {
        return clubNum;
    }

    public void setClubNum(long clubNum) {
        this.clubNum = clubNum;
    }

    public long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(long totalNum) {
        this.totalNum = totalNum;
    }

    public Double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Double totalFee) {
        this.totalFee = totalFee;
    }

    public long getTotalAliNum() {
        return totalAliNum;
    }

    public void setTotalAliNum(long totalAliNum) {
        this.totalAliNum = totalAliNum;
    }

    public Double getTotalAliFee() {
        return totalAliFee;
    }

    public void setTotalAliFee(Double totalAliFee) {
        this.totalAliFee = totalAliFee;
    }

    public long getTotalWxNum() {
        return totalWxNum;
    }

    public void setTotalWxNum(long totalWxNum) {
        this.totalWxNum = totalWxNum;
    }

    public Double getTotalWxFee() {
        return totalWxFee;
    }

    public void setTotalWxFee(Double totalWxFee) {
        this.totalWxFee = totalWxFee;
    }

    public long getConsumeNum() {
        return consumeNum;
    }

    public void setConsumeNum(long consumeNum) {
        this.consumeNum = consumeNum;
    }

    public Double getConsumeFee() {
        return consumeFee;
    }

    public void setConsumeFee(Double consumeFee) {
        this.consumeFee = consumeFee;
    }

    public long getFastPayNum() {
        return fastPayNum;
    }

    public void setFastPayNum(long fastPayNum) {
        this.fastPayNum = fastPayNum;
    }

    public Double getFastPayFee() {
        return fastPayFee;
    }

    public void setFastPayFee(Double fastPayFee) {
        this.fastPayFee = fastPayFee;
    }

    public long getItemCardNum() {
        return itemCardNum;
    }

    public void setItemCardNum(long itemCardNum) {
        this.itemCardNum = itemCardNum;
    }

    public Double getItemCardFee() {
        return itemCardFee;
    }

    public void setItemCardFee(Double itemCardFee) {
        this.itemCardFee = itemCardFee;
    }

    public long getItemPackageNum() {
        return itemPackageNum;
    }

    public void setItemPackageNum(long itemPackageNum) {
        this.itemPackageNum = itemPackageNum;
    }

    public Double getItemPackageFee() {
        return itemPackageFee;
    }

    public void setItemPackageFee(Double itemPackageFee) {
        this.itemPackageFee = itemPackageFee;
    }

    public long getOneYuanNum() {
        return oneYuanNum;
    }

    public void setOneYuanNum(long oneYuanNum) {
        this.oneYuanNum = oneYuanNum;
    }

    public Double getOneYuanFee() {
        return oneYuanFee;
    }

    public void setOneYuanFee(Double oneYuanFee) {
        this.oneYuanFee = oneYuanFee;
    }

    public long getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(long orderNum) {
        this.orderNum = orderNum;
    }

    public Double getOrderFee() {
        return orderFee;
    }

    public void setOrderFee(Double orderFee) {
        this.orderFee = orderFee;
    }

    public long getPaidCouponNum() {
        return paidCouponNum;
    }

    public void setPaidCouponNum(long paidCouponNum) {
        this.paidCouponNum = paidCouponNum;
    }

    public Double getPaidCouponFee() {
        return paidCouponFee;
    }

    public void setPaidCouponFee(Double paidCouponFee) {
        this.paidCouponFee = paidCouponFee;
    }

    public long getPaidOrderNum() {
        return paidOrderNum;
    }

    public void setPaidOrderNum(long paidOrderNum) {
        this.paidOrderNum = paidOrderNum;
    }

    public Double getPaidOrderFee() {
        return paidOrderFee;
    }

    public void setPaidOrderFee(Double paidOrderFee) {
        this.paidOrderFee = paidOrderFee;
    }

    public long getPaidServiceItemNum() {
        return paidServiceItemNum;
    }

    public void setPaidServiceItemNum(long paidServiceItemNum) {
        this.paidServiceItemNum = paidServiceItemNum;
    }

    public Double getPaidServiceItemFee() {
        return paidServiceItemFee;
    }

    public void setPaidServiceItemFee(Double paidServiceItemFee) {
        this.paidServiceItemFee = paidServiceItemFee;
    }

    public long getServiceItemNum() {
        return serviceItemNum;
    }

    public void setServiceItemNum(long serviceItemNum) {
        this.serviceItemNum = serviceItemNum;
    }

    public Double getServiceItemFee() {
        return serviceItemFee;
    }

    public void setServiceItemFee(Double serviceItemFee) {
        this.serviceItemFee = serviceItemFee;
    }

    public long getUserRechargeNum() {
        return userRechargeNum;
    }

    public void setUserRechargeNum(long userRechargeNum) {
        this.userRechargeNum = userRechargeNum;
    }

    public Double getUserRechargeFee() {
        return userRechargeFee;
    }

    public void setUserRechargeFee(Double userRechargeFee) {
        this.userRechargeFee = userRechargeFee;
    }

    public long getUserRewardNum() {
        return userRewardNum;
    }

    public void setUserRewardNum(long userRewardNum) {
        this.userRewardNum = userRewardNum;
    }

    public Double getUserRewardFee() {
        return userRewardFee;
    }

    public void setUserRewardFee(Double userRewardFee) {
        this.userRewardFee = userRewardFee;
    }
}
