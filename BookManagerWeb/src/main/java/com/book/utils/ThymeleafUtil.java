package com.book.utils;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.IContext;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.Writer;

public class ThymeleafUtil {
    //创建Thymeleaf模板引擎
    private static final TemplateEngine engine;
    static {
        engine = new TemplateEngine();
        //类加载器模板解析器
        ClassLoaderTemplateResolver r = new ClassLoaderTemplateResolver();
        r.setCharacterEncoding("UTF-8");
        engine.setTemplateResolver(r);
    }

    public static TemplateEngine getEngine(){
        return engine;
    }

    public static void doProcess(String template, IContext context, Writer writer){
        engine.process(template, context, writer);
    }
}
