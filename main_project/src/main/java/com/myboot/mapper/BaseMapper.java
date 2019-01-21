package com.myboot.mapper;

import java.util.List;

/**
 * Created by majf
 * 2018/8/15 18:32
 */
public interface BaseMapper {
    <T> int insertList(List<T> list);
}
