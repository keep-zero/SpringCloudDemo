package com.springcloud.demo;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * @description
 * @auther: lai.guanfu
 * @date: 2019-08-01 11:17
 */
public class TestExport {

    private Configuration configuration = null;

    public TestExport(){
        configuration = new Configuration(Configuration.VERSION_2_3_28);
        configuration.setDefaultEncoding("GBK");
    }

    public void createWord() throws IOException {
        Map<String,Object> dataMap=new HashMap<>();

        dataMap.put("morningName", "测试值班");
        dataMap.put("nightName", "测试副班");
        dataMap.put("leaderName", "测试带班");
        dataMap.put("dutyDate", "2014 02 06");
        dataMap.put("description", "测试描述");
        dataMap.put("dutyLog", "<p><span style=\"color: rgb(255, 0, 0);\"><strong>asdfdddddd</strong></span><strong>ddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddasdfddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddasdd</strong></p>");

        configuration.setClassForTemplateLoading(this.getClass(),"/");  //FTL文件所存在的位置

        Template t = configuration.getTemplate("duty_log.ftl","GBK"); //文件名

        File outFile = new File("C:/Users/12974/Desktop/"+Math.random()*10000+".doc");
        Writer out =  new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), Charset.forName("GBK")));

        try {
            t.process(dataMap, out);
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException {
        TestExport testExport = new TestExport();
        testExport.createWord();
    }


}
