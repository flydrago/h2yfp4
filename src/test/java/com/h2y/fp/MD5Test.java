package com.h2y.fp;

import com.h2y.security.MD5Util;

import java.io.IOException;

/**
 * @project: h2ybmg
 * @what:
 * @author: duanxg
 * @update: 2014/10/14 9:33
 * @Email: 
 */
public class MD5Test {

    public static void main(String args[]){

        String t = null;
        try {
            t = MD5Util.getStrMD5String("sa");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.printf("t = "+t);
    }
}
