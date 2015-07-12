package com.longtask.tea.execute;

import com.longtask.tea.freemarker.AbstractBulidTemplet;
import com.longtask.tea.freemarker.BulidTemplet;
import com.longtask.tea.yaml.ParseYamlConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Executor {
    private final static Logger logger   = LoggerFactory.getLogger(AbstractBulidTemplet.class);

    private static final String TEA_PATH = "com/longtask/tea/freemarker";

    /**
     * @param args
     */
    public static void main(String[] args) {
        logger.info("Create starting !");
        try {
            Executor executor = new Executor();

            List<Class<?>> classList = executor.getTemplateClass();
            executor.exec(classList);

        } catch (Exception e) {
            logger.error("Executor error:", e);
        }
        logger.info("Create end!");
    }

    /**
     * 遍历生成相关的模板文件
     * @param list
     * @throws Exception
     */
    protected void exec(List<Class<?>> list) {
        String[] tables = ParseYamlConfig.parseYaml().getSqlMap("tables").split(",");
        String sqlmapOnly = ParseYamlConfig.parseYaml().getCodePackageMap("sqlmapOnly");
        for (String table : tables) {
            for (Class<?> clazz : list) {
                if (sqlmapOnly.equals("true")) {
                    if (!clazz.getCanonicalName().endsWith("SqlMapBulidTemplet")) {
                        continue;
                    }
                }

                BulidTemplet bulidTemplet = null;
                try {
                    bulidTemplet = (BulidTemplet) clazz.newInstance();
                } catch (InstantiationException e) {
                    logger.error("InstantiationException :", e);
                    return;
                } catch (IllegalAccessException e) {
                    logger.error("IllegalAccessException :", e);
                    return;
                }
                bulidTemplet.buildSourceFile(table);
            }
        }
    }



    protected List<Class<?>> getTemplateClass() throws IOException,
                                                                  ClassNotFoundException,
                                                                  IllegalAccessException,
                                                                  InstantiationException {
        List<Class<?>> list = new ArrayList<Class<?>>();
        URL url = Executor.class.getResource("/com/longtask/tea/execute/Executor.class");
        String schema = url.getProtocol();
        if (!"jar".equals(schema))
            throw new IllegalArgumentException("Unsupported schema:" + schema);

        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
        JarFile jarFile = jarURLConnection.getJarFile();
        Enumeration<JarEntry> entries = jarFile.entries();
        while (entries.hasMoreElements()) {
            JarEntry jarEntry = entries.nextElement();
            if (jarEntry.getName().startsWith(TEA_PATH)) {
                if (jarEntry.isDirectory()) {
                    continue;
                }
                String className = jarEntry.getName().replace("/", ".");
                className = className.substring(0, className.length() - 6);
                Class<?> clazz = Thread.currentThread().getContextClassLoader()
                        .loadClass(className);
                if (!Modifier.isAbstract(clazz.getModifiers())
                        && Modifier.isPublic(clazz.getModifiers())
                        && (clazz.newInstance() instanceof BulidTemplet)) {
                    list.add(clazz);
                }
            }
        }

        return list;
    }

}
