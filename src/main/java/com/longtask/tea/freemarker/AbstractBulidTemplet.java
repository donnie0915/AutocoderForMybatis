package com.longtask.tea.freemarker;

import com.longtask.tea.freemarker.model.LowerFirstCharacter;
import com.longtask.tea.freemarker.model.UpperCharacter;
import com.longtask.tea.freemarker.model.UpperFirstCharacter;
import com.longtask.tea.yaml.ParseYamlConfig;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractBulidTemplet implements BulidTemplet {

    private final static Logger  logger = LoggerFactory.getLogger(AbstractBulidTemplet.class);

    private static Configuration freemarkerCfg;
    /**
     * 设置freemarker参数
     * @param dir
     * @throws IOException
     */
    static {
        freemarkerCfg = new Configuration();
        String usrDir = getBaseDir();
        try {
            freemarkerCfg.setDirectoryForTemplateLoading(new File(usrDir + "/template"));
        } catch (IOException e) {
            logger.error("freemarkerCfg.setDirectoryForTemplateLoading error", e);
        }
        freemarkerCfg.setObjectWrapper(new DefaultObjectWrapper());
        freemarkerCfg.setDefaultEncoding("UTF-8");
        freemarkerCfg.setSharedVariable("upperFirstChar", new UpperFirstCharacter());//设置公用变量
        freemarkerCfg.setSharedVariable("lowerFirstChar", new LowerFirstCharacter());//设置公用变量
        freemarkerCfg.setSharedVariable("upperChar", new UpperCharacter());
    }

    protected boolean buildJavaFile(String templateFileName, Map<?, ?> paramMap, String outputDir, String fileName) {
        try {
            Template template = freemarkerCfg.getTemplate(templateFileName);
            template.setEncoding("UTF-8");
            // 创建生成文件目录
            File file = new File(outputDir);
            if (!file.exists()) {
                file.mkdirs();
            }
            if (!outputDir.endsWith("/")) {
                outputDir = outputDir + "/";
            }
            File javaFile = new File(outputDir + fileName);
            logger.info("---------------------------Create file is " + javaFile);
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(javaFile), "UTF-8"));
            template.process(paramMap, out);
            out.flush();
            return true;
        } catch (TemplateException ex) {
            logger.error("Build Error : " + templateFileName, ex);
            return false;
        } catch (IOException e) {
            logger.error("Build Error : " + templateFileName, e);
            return false;
        }
    }

    protected Map<String, Object> getParamMap() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("author", ParseYamlConfig.parseYaml().getName());
        paramMap.put("version", ParseYamlConfig.parseYaml().getVersion());
        paramMap.put("createTime", format(new Date(), "yyyy-MM-dd"));
        return paramMap;
    }

    protected String format(Date date, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    protected static String getBaseDir() {
        String usrDir = System.getProperty("user.dir");
        if (usrDir.endsWith("/lib")) {
            usrDir = usrDir.substring(0, usrDir.length() - 5);
        }

        return usrDir;
    }

    protected static String getMoudleDir(String moduleName) {

        if (moduleName == null) {
            moduleName = "dao";
        }

        return "/" +  moduleName;
    }
}
