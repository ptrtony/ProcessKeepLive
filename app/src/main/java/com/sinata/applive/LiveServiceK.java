package com.sinata.applive;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class LiveServiceK extends AbsLiveService {
    public LiveServiceK() {
    }

    private void e(Intent var1) {
        LiveLog.a(new String[]{"AbsLiveService", "addService()"});
        if (var1 != null) {
            String[] var3;
            try {
                var3 = (String[])var1.getSerializableExtra("extra_service");
            } catch (Exception var2) {
                return;
            }

            if (var3 != null && var3.length != 0) {
                AppLiveManagerK.m(this).c(var3);
            }

        }
    }

    private void f(Intent var1) {
        if (var1 != null) {
            int var2 = var1.getIntExtra("extra_command", 0);
            if ((var2 & 1) == 1) {
                this.e(var1);
            }

            if ((var2 & 2) == 2) {
                this.h(var1);
            }

            if ((var2 & 4) == 4) {
                this.g(var1);
            }

            AppLiveManagerK.m(this).h();
        }
    }

    private void g(Intent var1) {
        AppLiveManagerK.m(this).g();
    }

    private void h(Intent var1) {
        LiveLog.a(new String[]{"AbsLiveService", "removeService()"});
        if (var1 != null) {
            String[] var3;
            try {
                var3 = (String[])var1.getSerializableExtra("extra_service");
            } catch (Exception var2) {
                return;
            }

            if (var3 != null && var3.length != 0) {
                AppLiveManagerK.m(this).f(var3);
            }

        }
    }

    public static void i(Context var0, int var1, String[] var2) {
        Intent var3 = new Intent(var0, LiveServiceK.class);
        var3.putExtra("extra_command", var1);
        if (var2 != null && var2.length > 0) {
            var3.putExtra("extra_service", var2);
        }

        try {
            var0.startService(var3);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }

    public Class<? extends AbsInnerService> c() {
        return LiveServiceK.InnerService.class;
    }

    @Nullable
    public IBinder onBind(Intent var1) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
    }

    public int onStartCommand(Intent var1, int var2, int var3) {
        this.f(var1);
        return super.onStartCommand(var1, var2, var3);
    }

    public static class InnerService extends AbsInnerService {
        public InnerService() {
        }
    }
}
