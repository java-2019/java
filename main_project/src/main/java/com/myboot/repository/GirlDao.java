package com.myboot.repository;

import com.myboot.entity.Girl;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by majf
 * 2018/7/20 11:37
 */
public interface GirlDao extends JpaRepository<Girl, Integer> {
}
