package com.myboot.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * This class corresponds to the database table xmd_club_payment_stat
 * <p>
 * 会所支付统计 
 * @author lxy@xiaomodo.com created at Fri Jul 27 20:41:45 CST 2018
 * 
 */
public class ClubPaymentStat extends BaseModal implements Serializable {


  /**
   * 
   * 
   * This field corresponds to the database column xmd_club_payment_stat.id.
   * <p>
   * 
   */
  private long id;

  /**
   * 
   * 会所id
   * This field corresponds to the database column xmd_club_payment_stat.club_id.
   * <p>
   * 
   */
  private String clubId;

  /**
   * 
   * 每日支付笔数
   * This field corresponds to the database column xmd_club_payment_stat.count.
   * <p>
   * 
   */
  private int count;

  /**
   * 
   * 每日支付金额(分)
   * This field corresponds to the database column xmd_club_payment_stat.amount.
   * <p>
   * 
   */
  private int amount;

  /**
   * 
   * 每日支付人数
   * This field corresponds to the database column xmd_club_payment_stat.persons.
   * <p>
   * 
   */
  private int persons;

  /**
   * 
   * 前7日支付笔数均值
   * This field corresponds to the database column xmd_club_payment_stat.total_count.
   * <p>
   * 
   */
  private int totalCount;

  /**
   * 
   * 前7日支付金额均值
   * This field corresponds to the database column xmd_club_payment_stat.total_amount.
   * <p>
   * 
   */
  private int totalAmount;

  /**
   * 
   * 前7日支付人数均值
   * This field corresponds to the database column xmd_club_payment_stat.total_persons.
   * <p>
   * 
   */
  private int totalPersons;

  /**
   * 
   * 统计日期
   * This field corresponds to the database column xmd_club_payment_stat.stat_data.
   * <p>
   * 
   */
  private Date statDate;

  /**
   * 
   * 修改时间
   * This field corresponds to the database column xmd_club_payment_stat.modify_time.
   * <p>
   * 
   */
  private Date modifyTime;


  /**
   * 
   * 
   * This method returns the value of the database column xmd_club_payment_stat.id
   * 
   * @return the value of xmd_club_payment_stat.id
   * <p>
   * 
   */
  public long getId() {
    return id;
  }

  /**
   * 
   * 
   * This method sets the value of the database column xmd_club_payment_stat.id
   * 
   * @param id the value for xmd_club_payment_stat.id
   * <p>
   * 
   */
  public void setId(long id) {
    this.id = id;
  }


  /**
   * 
   * 会所id
   * This method returns the value of the database column xmd_club_payment_stat.club_id
   * 
   * @return the value of xmd_club_payment_stat.club_id
   * <p>
   * 
   */
  public String getClubId() {
    return clubId;
  }

  /**
   * 
   * 会所id
   * This method sets the value of the database column xmd_club_payment_stat.club_id
   * 
   * @param clubId the value for xmd_club_payment_stat.club_id
   * <p>
   * 
   */
  public void setClubId(String clubId) {
    this.clubId = clubId;
  }


  /**
   * 
   * 每日支付笔数
   * This method returns the value of the database column xmd_club_payment_stat.count
   * 
   * @return the value of xmd_club_payment_stat.count
   * <p>
   * 
   */
  public int getCount() {
    return count;
  }

  /**
   * 
   * 每日支付笔数
   * This method sets the value of the database column xmd_club_payment_stat.count
   * 
   * @param count the value for xmd_club_payment_stat.count
   * <p>
   * 
   */
  public void setCount(int count) {
    this.count = count;
  }


  /**
   * 
   * 每日支付金额(分)
   * This method returns the value of the database column xmd_club_payment_stat.amount
   * 
   * @return the value of xmd_club_payment_stat.amount
   * <p>
   * 
   */
  public int getAmount() {
    return amount;
  }

  /**
   * 
   * 每日支付金额(分)
   * This method sets the value of the database column xmd_club_payment_stat.amount
   * 
   * @param amount the value for xmd_club_payment_stat.amount
   * <p>
   * 
   */
  public void setAmount(int amount) {
    this.amount = amount;
  }


  /**
   * 
   * 每日支付人数
   * This method returns the value of the database column xmd_club_payment_stat.persons
   * 
   * @return the value of xmd_club_payment_stat.persons
   * <p>
   * 
   */
  public int getPersons() {
    return persons;
  }

  /**
   * 
   * 每日支付人数
   * This method sets the value of the database column xmd_club_payment_stat.persons
   * 
   * @param persons the value for xmd_club_payment_stat.persons
   * <p>
   * 
   */
  public void setPersons(int persons) {
    this.persons = persons;
  }


  /**
   * 
   * 前7日支付笔数均值
   * This method returns the value of the database column xmd_club_payment_stat.total_count
   * 
   * @return the value of xmd_club_payment_stat.total_count
   * <p>
   * 
   */
  public int getTotalCount() {
    return totalCount;
  }

  /**
   * 
   * 前7日支付笔数均值
   * This method sets the value of the database column xmd_club_payment_stat.total_count
   * 
   * @param totalCount the value for xmd_club_payment_stat.total_count
   * <p>
   * 
   */
  public void setTotalCount(int totalCount) {
    this.totalCount = totalCount;
  }


  /**
   * 
   * 前7日支付金额均值
   * This method returns the value of the database column xmd_club_payment_stat.total_amount
   * 
   * @return the value of xmd_club_payment_stat.total_amount
   * <p>
   * 
   */
  public int getTotalAmount() {
    return totalAmount;
  }

  /**
   * 
   * 前7日支付金额均值
   * This method sets the value of the database column xmd_club_payment_stat.total_amount
   * 
   * @param totalAmount the value for xmd_club_payment_stat.total_amount
   * <p>
   * 
   */
  public void setTotalAmount(int totalAmount) {
    this.totalAmount = totalAmount;
  }


  /**
   * 
   * 前7日支付人数均值
   * This method returns the value of the database column xmd_club_payment_stat.total_persons
   * 
   * @return the value of xmd_club_payment_stat.total_persons
   * <p>
   * 
   */
  public int getTotalPersons() {
    return totalPersons;
  }

  /**
   * 
   * 前7日支付人数均值
   * This method sets the value of the database column xmd_club_payment_stat.total_persons
   * 
   * @param totalPersons the value for xmd_club_payment_stat.total_persons
   * <p>
   * 
   */
  public void setTotalPersons(int totalPersons) {
    this.totalPersons = totalPersons;
  }


  /**
   * 
   * 统计日期
   * This method returns the value of the database column xmd_club_payment_stat.stat_data
   * 
   * @return the value of xmd_club_payment_stat.stat_data
   * <p>
   * 
   */
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
  public Date getStatDate() {
    return statDate;
  }

  /**
   * 
   * 统计日期
   * This method sets the value of the database column xmd_club_payment_stat.stat_data
   * 
   * @param statDate the value for xmd_club_payment_stat.stat_data
   * <p>
   * 
   */
  public void setStatDate(Date statDate) {
    this.statDate = statDate;
  }


  /**
   * 
   * 修改时间
   * This method returns the value of the database column xmd_club_payment_stat.modify_time
   * 
   * @return the value of xmd_club_payment_stat.modify_time
   * <p>
   * 
   */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
  public Date getModifyTime() {
    return modifyTime;
  }

  /**
   * 
   * 修改时间
   * This method sets the value of the database column xmd_club_payment_stat.modify_time
   * 
   * @param modifyTime the value for xmd_club_payment_stat.modify_time
   * <p>
   * 
   */
  public void setModifyTime(Date modifyTime) {
    this.modifyTime = modifyTime;
  }

}
