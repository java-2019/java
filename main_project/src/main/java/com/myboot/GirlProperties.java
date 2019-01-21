package com.myboot;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by majf
 * 2018/7/19 11:06
 */
@Component
@ConfigurationProperties(prefix = "girl")
public class GirlProperties {

    private String figure;

    private  Integer age;


    public String getFigure() {
        return figure;
    }

    public void setFigure(String cup) {
        this.figure = cup;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
