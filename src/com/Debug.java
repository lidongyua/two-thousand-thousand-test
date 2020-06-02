package com;

import com.cui.CuiStarter;

/**
 * @author lidongy
 * @version 10.0
 * Created by lidongy on 2020/5/9
 */
public class Debug {
    public static void main(String[] args) throws Exception {
        TwoThousandThousandServer.getInstance().init();
        CuiStarter.start();
    }
}
