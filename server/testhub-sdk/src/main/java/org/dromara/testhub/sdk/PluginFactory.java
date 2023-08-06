
package org.dromara.testhub.sdk;


import com.goddess.nsrule.core.constant.RuleException;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.TreeMap;

@Component("pluginFactory")
public class PluginFactory {

    static Map<String, Plugin> PLUGIN_MAP = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    static {
        ServiceLoader<Plugin> s = ServiceLoader.load(Plugin.class);
        Iterator<Plugin> iterator = s.iterator();
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
