package com.example.weixin_manage.support;

public class StringUtils {

   public static Boolean  isNotBlank(String name){

       if (name==null){
           return false;
       }
       if (name.trim().length()==0){
           return false;
       }
       return true;

    }
}
