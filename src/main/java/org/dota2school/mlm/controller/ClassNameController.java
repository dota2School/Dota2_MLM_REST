package org.dota2school.mlm.controller;

import org.dota2school.mlm.domain.ClassName;
import org.dota2school.mlm.model.ClassNameEntry;
import org.dota2school.mlm.respository.ClassNameRespository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
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
    public ClassNameEntry queryClassName(){
        List<ClassName> classNames =  classNameRespository.findAll(new Sort(new Sort.Order(Sort.Direction.ASC,"order")));
        List<ClassName> mengxin = new ArrayList<>();
        List<ClassName> tigao = new ArrayList<>();
        classNames.forEach(c->{
            if(c.getType().equals("萌新")){
                mengxin.add(c);
            }else {
                tigao.add(c);
            }
        });
        mengxin.sort(Comparator.comparingInt(m->m.getOrder()));
        tigao.sort(Comparator.comparingInt(m ->m.getOrder()));
        ClassNameEntry classNameEntry = new ClassNameEntry();
        classNameEntry.setMengxin(mengxin.stream().map(d->d.getClassName()).collect(Collectors.toList()));
        classNameEntry.setTigao(tigao.stream().map(d->d.getClassName()).collect(Collectors.toList()));
        return classNameEntry;
    }
}
