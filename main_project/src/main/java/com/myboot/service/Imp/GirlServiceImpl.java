package com.myboot.service.Imp;

import com.myboot.entity.Girl;
import com.myboot.repository.GirlDao;
import com.myboot.service.GirlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 *
 * Created by majf
 * 2018/7/20 11:36
 */
@Service
public class GirlServiceImpl implements GirlService {

    @Autowired
    GirlDao girlDao;

    @Override
    public List<Girl> girlList() {
        return girlDao.findAll();
    }

    @Override
    public Girl girlAdd(Girl girl) {
        girl.setCreateTime(new Date());
        girl.setModifyTime(new Date());
        return girlDao.save(girl);
    }
}
