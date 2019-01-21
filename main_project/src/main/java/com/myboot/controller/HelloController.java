package com.myboot.controller;

import com.myboot.GirlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by majf
 * 2018/7/18
 */

@RestController
@RequestMapping(value = "/hello")
public class HelloController {


    @Autowired
    GirlProperties girlProperties;


    @RequestMapping(value = "/{id}/say", method = RequestMethod.GET)
    public String say(@PathVariable("id") Integer id,
                      @RequestParam(value = "name", required = false, defaultValue = "defaultName") String name){
        return "hello name: "  + name + " id : " + id + " Figure : " + girlProperties.getFigure() + " Age : " + girlProperties.getAge();
    }
}
