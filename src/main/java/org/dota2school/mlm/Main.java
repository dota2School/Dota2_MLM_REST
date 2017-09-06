package org.dota2school.mlm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

/**
 * Created by nt on 2017/7/7.
 */
@SpringBootApplication
public class Main {
    static{
        TimeZone.setDefault(TimeZone.getTimeZone("PRC"));
    }
    public static void main(String[] args){
        SpringApplication.run(Main.class);
    }
}
