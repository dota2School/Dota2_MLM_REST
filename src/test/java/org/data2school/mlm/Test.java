package org.data2school.mlm;

import org.dota2school.mlm.model.BindRequest;
import org.dota2school.mlm.util.G;

/**
 * Created by nt on 2017/9/8.
 */
public class Test {

    public static void main(String[] args){
        String s="{\"nickName\":\"测试用户\",\"roleType\":1,\"classType\":\"萌新班\",\"rankScore\":\"12312\",\"contents\":\"基础知识_1号位_5号位\",\"steamID\":\"123123\",\"session\":\"oUtn60MfarHE-4mGKcbMKwqh0srE\"}";
        BindRequest request = G.G.fromJson(s,BindRequest.class);
        System.out.println(request.getNikeName());
    }

}
