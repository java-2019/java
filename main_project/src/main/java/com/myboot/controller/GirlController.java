package com.myboot.controller;

import com.myboot.entity.Girl;
import com.myboot.service.GirlService;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;
import java.util.List;

/**
 * Created by majf
 * 2018/7/20 11:18
 */
@RestController
@RequestMapping(value = "/girl")
public class GirlController {

    @Autowired
    GirlService girlService;

    private static SecureRandom random = new SecureRandom();

    /**
     * 查询信息
     * @return
     */

    @GetMapping(value = "/list")
    public List<Girl> girlList(){
        List<Girl> list = girlService.girlList();
        return list;
    }

    @PostMapping(value = "/add")
    public Girl girlAdd(Girl girl){
        Girl result = girlService.girlAdd(girl);
        return result;
    }

    @PostMapping(value = "/random")
    public void random(){
        byte[] bytes = new byte[8];
        random.nextBytes(bytes);
        System.out.println(bytes);
        String hex = Hex.encodeHexString(bytes);
        System.out.println(bytes);
        System.out.println(hex);
    }


//    @RequestMapping(value = "/add",method = RequestMethod.POST)
//    public Girl girlAdd(@RequestParam("age") Integer age,
//                        @RequestParam("figure") String figure,
//                        @RequestParam("live") String live){
//        Girl girl = new Girl();
//        girl.setAge(age);
//        girl.setFigure(figure);
//        girl.setLive(live);
//        Girl result = girlService.girlAdd(girl);
//        return result;
//    }

}
