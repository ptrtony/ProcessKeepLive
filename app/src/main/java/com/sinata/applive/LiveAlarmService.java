package com.sinata.applive;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.util.concurrent.TimeUnit;

public class LiveAlarmService extends Service {
    private static final long a;

    static {
        a = TimeUnit.MINUTES.toMillis(1L);
    }

    public LiveAlarmService() {
    }

    public static void a(Context var0) {
        LiveLog.a(new String[]{"LiveAlarmService", "startLiveAlarmService()"});
        AlarmManager var1 = (AlarmManager)var0.getSystemService(Context.ALARM_SERVICE);
        PendingIntent var6 = PendingIntent.getService(var0, 184323, new Intent(var0, LiveAlarmService.class), 134217728);
        if (var1 != null) {
            var1.cancel(var6);
            long var2 = System.currentTimeMillis();
            long var4 = a;
            var1.setRepeating(AlarmManager.RTC, var2 + var4, var4 * 2L, var6);
            var4 = System.currentTimeMillis();
            var2 = a;
            var1.setRepeating(AlarmManager.RTC, var4 + 4L * var2, var2 * 7L, var6);
            var2 = System.currentTimeMillis();
            var4 = a;
            var1.setRepeating(AlarmManager.RTC, var2 + 6L * var4, var4 * 15L, var6);
            var4 = System.currentTimeMillis();
            var2 = a;
            var1.setRepeating(AlarmManager.RTC_WAKEUP, var4 + var2, var2 * 13L, var6);
            var2 = System.currentTimeMillis();
            var4 = a;
            var1.setRepeating(AlarmManager.RTC_WAKEUP, var2 + 2L * var4, var4 * 30L, var6);
            LiveLog.a(new String[]{"LiveAlarmService", "startLiveAlarmService end()"});
        }

    }

    @Nullable
    public IBinder onBind(Intent var1) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        LiveLog.a(new String[]{"LiveAlarmService", "onCreate()"});
    }

    public int onStartCommand(Intent var1, int var2, int var3) {
        LiveLog.a(new String[]{"LiveAlarmService", "onStartCommand()"});
        AppLiveManager.getInstance().l(0L);
        this.stopSelf();
        return super.onStartCommand(var1, var2, var3);
    }
}
