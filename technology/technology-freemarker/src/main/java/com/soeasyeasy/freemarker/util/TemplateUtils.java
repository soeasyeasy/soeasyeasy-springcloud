package com.soeasyeasy.freemarker.util;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.StringWriter;

public class TemplateUtils {

    public static String processTemplateIntoString(Template template, Object model) throws Exception {
        try (StringWriter writer = new StringWriter()) {
            template.process(model, writer);
            return writer.toString();
        }
    }

    public static String processTemplateIntoString(Configuration cfg, String templateName, Object model) throws Exception {
        Template template = cfg.getTemplate(templateName);
        return processTemplateIntoString(template, model);
    }
}