package com;

import com.log.LogFactory;
import com.pitch.PitchPieFactory;
import com.utils.FileIOUtils;

/**
 * @author lidongy
 * @version 10.0
 * Created by lidongy on 2020/5/9
 */
public class TwoThousandThousandServer {
    private static TwoThousandThousandServer instance = new TwoThousandThousandServer();

    public static TwoThousandThousandServer getInstance() {
        if (instance != null) {
            return instance;
        }
        return new TwoThousandThousandServer();
    }


    private TwoThousandThousandServer() {
    }

    public void init() throws Exception {
        this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        Global.init();
    }

    public void pitchPie() throws Exception {
        PitchPieFactory.pitch(Global.globalConfig.get("location.project"));
    }

    public void getCuiHelp() throws Exception {
        String path = Global.globalConfig.get("location.cui.helper");
        String content = FileIOUtils.readString(path);
        LogFactory.info(content);
    }
}
