package com.sinata.applive;


import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import androidx.annotation.Keep;

import java.lang.reflect.Method;

@Keep
public class GlobalConfig {

    @SuppressLint({"StaticFieldLeak"})
    private static final GlobalConfig sGlobalConfig = new GlobalConfig();
    private Context mApplication;
    private boolean isDebug;
    private Context getGlobalContextByReflect() {
        Class clazz;
        try {
            clazz = Class.forName("android.app.ActivityThread");
            if (clazz == null) {
                return null;
            } else {
                Method method = clazz.getMethod("currentApplication");
                method.setAccessible(true);
                Application application = (Application) method.invoke(null);
                if (application != null) {
                    return application.getApplicationContext();
                }
                method = clazz.getMethod("currentActivityThread");
                method.setAccessible(true);
                Object object = method.invoke(null);
                application = (Application) clazz.getMethod("getApplication").invoke(object);
                return application.getApplicationContext();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Context getGlobalContext() {
        if (this.mApplication == null) {
            Context context = this.getGlobalContextByReflect();
            if (context != null) {
                init(context);
            }
        }
        return mApplication;
    }

    public GlobalConfig init(Context context){
        if (this.mApplication != null){
            return this;
        }else if (context != null){
            if (context instanceof Application){
                this.mApplication = context;
            }else{
                this.mApplication = context.getApplicationContext();
            }
            initDebug(context);
            InitManager.a().b();
            return this;
        }else {
            throw new IllegalArgumentException("The param of context must be not null.");
        }

    }

    private void initDebug(Context var1) {
        boolean var2;
        if ((var1.getApplicationInfo().flags & 2) != 0) {
            var2 = true;
        } else {
            var2 = false;
        }
        this.isDebug = var2;
    }


    public static GlobalConfig getInstance() {
        return sGlobalConfig;
    }
}
