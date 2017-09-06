package org.dota2school.mlm.controller;

import org.dota2school.mlm.respository.ClassNameRespository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by nt on 2017/9/3.
 */
@RestController
@RequestMapping("/api/mlm")
public class ClassNameController {
    private static final Logger LOG = LoggerFactory.getLogger(ClassNameController.class);

    @Autowired
    private ClassNameRespository classNameRespository;


    @RequestMapping("class_name")
    public List<String> queryClassName(){
        return classNameRespository.findAll(new Sort(new Sort.Order(Sort.Direction.ASC,"order"))).stream().map(d->{
            return d.getClassName();
        }).collect(Collectors.toList());
    }
}
