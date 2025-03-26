package com.soeasyeasy.auth.core;

import java.io.File;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathUtil {

    public static Path getCallerModuleRootPath() {
        try {
            // 获取当前线程的堆栈跟踪
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            StackTraceElement element = stackTrace[stackTrace.length - 1];
            Class<?> callerClass = Class.forName(element.getClassName());
            String classPath = callerClass.getProtectionDomain().getCodeSource().getLocation().getPath();
            File classDir = new File(URLDecoder.decode(classPath, StandardCharsets.UTF_8));
            return Paths.get(classPath.substring(1)).getParent().getParent();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}