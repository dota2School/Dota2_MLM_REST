package org.dota2school.mlm.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by nt on 2017/7/19.
 */
@RestController
@RequestMapping("/api/mlm")
public class CommonController {

    @RequestMapping(value = "/common",method = RequestMethod.POST)
    public String common(@RequestParam(name = "avatar_src") String avatar_src,
                         @RequestParam(name = "avatar_data") String avatar_data,
                         @RequestParam(name = "avatar_file") String avatar_file){

        System.out.println("avatar_src = "+avatar_src);
        System.out.println("avatar_data = "+avatar_data);
        System.out.println("avatar_file = "+avatar_file);
        return "";
    }

}
