package com.cui;

import com.Global;
import com.TwoThousandThousandServer;
import com.log.LogFactory;

import java.util.Scanner;

/**
 * @author lidongy
 * @version 10.0
 * Created by lidongy on 2020/5/9
 */
public class CuiStarter {

    public static void start() throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean next = true;
        LogFactory.info("欢迎来到精准测试平台");
        LogFactory.info("Version:" + Global.globalConfig.get("version"));
        while (next) {
            String command = scanner.nextLine();
            switch (command.toLowerCase()) {
                case "help":
                    TwoThousandThousandServer.getInstance().getCuiHelp();
                    break;
                case "pitch":
                    TwoThousandThousandServer.getInstance().pitchPie();
                    break;
                case "":
                    break;
                case "exit":
                    next = false;
                    break;
                default:
                    LogFactory.info("无效指令");
            }
        }
    }
}
