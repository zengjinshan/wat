package com.fh.entity;

/**
 * Created by Administrator on 2018/3/11.
 */
public class Test {

    public static void  main(String[] args){
        String s="12,33,3,4,5,6,";
        int i = s.lastIndexOf(",");
        System.out.println(i);
        System.out.print(s.substring(0,s.length()-1));
    }
}
