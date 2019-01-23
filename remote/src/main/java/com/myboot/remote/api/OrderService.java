package com.myboot.remote.api;

/**
 * Created by majf
 * 2018/12/25 10:28
 */
public interface OrderService {

    Boolean rushToBuy(String goodsCode, final String userId) throws Exception;

    String createOrder();

}
