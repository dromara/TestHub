
package org.dromara.testhub.sdk;


import com.goddess.nsrule.core.constant.RuleException;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.URL;
import java.util.*;

@Component("pluginFactory")
public class PluginFactory {


    static Map<String, Plugin> PLUGIN_MAP = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    static {
        ServiceLoader<Plugin> serviceLoader;
        URL pluginUrl = PluginFactory.class.getClassLoader().getResource("plugin");
        if(pluginUrl!=null){
            String resourcesPath = pluginUrl.getFile();
            PluginClassLoader classLoader = new PluginClassLoader(new URL[0]);
            File pluginDirectory = new File(resourcesPath);

            if (pluginDirectory.exists() && pluginDirectory.isDirectory()) {
                for (File jarFile : pluginDirectory.listFiles()) {
                    if (jarFile.isFile() && jarFile.getName().endsWith(".jar")) {
                        try {
                            classLoader.addJarFile(jarFile.getAbsolutePath());
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
            serviceLoader = ServiceLoader.load(Plugin.class,classLoader);
        }else {
            serviceLoader = ServiceLoader.load(Plugin.class);
        }


        Iterator<Plugin> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            Plugin plugin = iterator.next();
            PLUGIN_MAP.put(plugin.getType(), plugin);
        }
    }

    public static Plugin getHandler(String type) {
        if (PLUGIN_MAP.containsKey(type)) {
            return PLUGIN_MAP.get(type);
        } else {
            throw new RuleException("找不到的行为类型:"+type);
        }
    }
}
