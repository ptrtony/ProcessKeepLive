package com.sinata.applive;


import android.annotation.SuppressLint;
import android.app.Application;
import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;

public abstract class AppLiveManager {
    @SuppressLint({"StaticFieldLeak"})
    private static AppLiveManager appLiveManager;
    private static AppLiveManager.NotificationFactory f;
    private static AppLiveManager.ServiceLauncher g;
    private HandlerThread a = new HandlerThread("app_live_thread");
    private Context b;
    private volatile boolean c;
    private boolean d;


    public Context getContext(){
        return b;
    }
    public AppLiveManager() {

    }

    @SafeVarargs
    private static String[] c(Class<? extends Service>... var0) {
        if (var0 != null && var0.length != 0) {
            String[] var1 = new String[var0.length];

            for(int var2 = 0; var2 < var0.length; ++var2) {
                if (var0[var2] != null) {
                    var1[var2] = var0[var2].getName();
                } else {
                    var1[var2] = "";
                }
            }

            return var1;
        } else {
            return null;
        }
    }

    public final boolean h() {
        return this.d;
    }



    public static AppLiveManager getInstance(){
        if (appLiveManager == null){
            Context context;
//            ApplicationInfo applicationInfo;
            context = GlobalConfig.getInstance().getGlobalContext();
//            applicationInfo = context.getApplicationInfo();
//            int version;
//            if (applicationInfo == null){
//                version = Build.VERSION_CODES.O;
//            }else{
//                version = applicationInfo.targetSdkVersion;
//            }
            try{
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
                    appLiveManager = new AppLiveManagerK();
                }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    appLiveManager = new AppLiveManagerO();
                }else{
                    appLiveManager = new AppLiveManagerL();
                }
                appLiveManager.b = context.getApplicationContext();
                appLiveManager.i(appLiveManager.b);
            }catch (Exception e){
                e.printStackTrace();
            }

        }else{
            return appLiveManager;
        }
        return null;
    }

    @SafeVarargs
    public final AppLiveManager a(Class<? extends Service>... var1) {
        this.b(c(var1));
        return this;
    }

    public abstract AppLiveManager b(String... var1);

    public final AppLiveManager.NotificationFactory f() {
        return f;
    }

    public final AppLiveManager.ServiceLauncher g() {
        return g;
    }

    public final void k() {
        this.l(20000L);
    }
    public final void l(long var1) {
        synchronized(this){
            boolean var3;
            try {
                var3 = this.c;
                if (var3){
                    return;
                }
                this.c = true;
                if (this.h()) {
                    OnePiexlManager.d((Application)this.b);
                }
                this.a.start();
                Handler var4 = new Handler(this.a.getLooper());
                AppLiveManager.InitRunnable var18 = new AppLiveManager.InitRunnable(getInstance().b);
                var18.a(this.h());
                var4.postDelayed(var18, var1);
                this.j();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    static class InitRunnable implements Runnable {
        Context a;
        boolean b;

        InitRunnable(Context var1) {
            this.a = var1;
        }

        void a(boolean var1) {
            this.b = var1;
        }

        public void run() {
            if (Build.VERSION.SDK_INT >= 21) {
                LiveLog.a(new String[]{"AppLiveManager init", "startJobService()"});
                LiveJobService.b(this.a);
            }

            if (Build.VERSION.SDK_INT <= 22) {
                LiveLog.a(new String[]{"AppLiveManager init", "startAlarmService()"});
                LiveAlarmService.a(this.a);
            }

            if (this.b) {
                LiveLog.a(new String[]{"AppLiveManager init", "startAlarmService()"});
            }

        }
    }

    protected abstract void i(Context var1);

    protected abstract void j();

    public interface Checker {
        void a(Context var1);
    }


    public interface NotificationFactory {
        int a(Context var1);

        String b(Context var1);

        String c(Context var1);

        int d(Context var1);

        Notification e(Context var1);
    }

    public interface ServiceLauncher {
        boolean a(Intent var1);
    }
}
