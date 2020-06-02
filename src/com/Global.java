package com;

import com.log.LogFactory;
import com.utils.FileIOUtils;

import java.io.File;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author lidongy
 * @version 10.0
 * Created by lidongy on 2020/5/9
 */
public class Global {
    public static String projectPath;
    public static Map<String, String> globalConfig;

    public static void init() {
        File file = new File(Global.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        try {
            projectPath = URLDecoder.decode(file.getParentFile().getPath(), "UTF-8");
        } catch (Exception e) {
            LogFactory.error(e.getMessage());
        }

        globalConfig = new HashMap<>();
        try {
            Set<Map.Entry<Object, Object>> configEntrySet = FileIOUtils.readPropertiesFile(projectPath + "/config.properties").entrySet();
            for (Map.Entry<Object, Object> entry : configEntrySet) {
                globalConfig.put((String) entry.getKey(), (String) entry.getValue());
            }
        } catch (Exception e) {
            LogFactory.error(e.getMessage());
        }
    }


}
