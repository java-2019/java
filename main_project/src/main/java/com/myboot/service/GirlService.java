package com.myboot.service;

import com.myboot.entity.Girl;

import java.util.List;

/**
 * Created by majf
 * 2018/7/20 11:31
 */
public interface GirlService {
    List<Girl> girlList();

    Girl girlAdd(Girl girl);
}
