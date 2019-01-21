package com.shicirili.fastservice.common.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class LogUtil {

    private static Logger logger = LoggerFactory.getLogger(LogUtil.class);

    public static void error(String remark , Throwable e){
        String stackTrace = getErrorStackTrace(e);
        error(remark+" "+stackTrace);
    }

    private static String getErrorStackTrace(Throwable e) {
        StringWriter sw =null;
        PrintWriter pw =null;
        try{
            sw=new StringWriter();
            pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
        }finally {
            if (sw!=null){
                try {
                    sw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (pw!=null){
                pw.close();
            }
        }
        return sw.toString();
    }

    public static void error(String remark) {
        logger.error(remark);
    }

    public static void info(String info) {
        logger.info(info);
    }

}
