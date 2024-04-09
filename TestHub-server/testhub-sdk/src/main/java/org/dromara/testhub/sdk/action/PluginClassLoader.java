package org.dromara.testhub.sdk.action;


import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class PluginClassLoader extends URLClassLoader {
    public PluginClassLoader(URL[] urls) {
        super(urls);
    }

    public void addJarFile(String jarFilePath) throws Exception {
        File jarFile = new File(jarFilePath);
        if (jarFile.exists()) {
            URL jarUrl = jarFile.toURI().toURL();
            addURL(jarUrl);
        } else {
            throw new Exception("JAR file does not exist: " + jarFilePath);
        }
    }
}
