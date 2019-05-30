package com.fengkai.zhouyang.yangyanghongkong.config;

import java.lang.reflect.Field;

public class Config {

    public static boolean debug = isDebug();

    public static boolean isBusness = isUser();

    private static boolean isDebug() {
        boolean result = false;
        try {
            Class c = Class.forName("com.fengkai.zhouyang.yangyanghongkong.BuildConfig");
            Field f = c.getField("DEBUG");
            result = f.getBoolean(c);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static boolean isUser() {
        boolean result = true;
        try {
            Class c = Class.forName("com.fengkai.zhouyang.yangyanghongkong.BuildConfig");
            Field f = c.getField("FLAVOR");
            String env = (String) f.get(c);
            result = "business".equals(env);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return result;
    }
}
