package com.sinata.applive;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;


@RequiresApi(Build.VERSION_CODES.O)
public class LiveServiceO extends Service {
   public LiveServiceO() {
   }

   protected void a() {
      synchronized(this){}

      try {
         LiveLog.a(new String[]{"AbsLiveService", "closeAmberForeground()"});

         try {
            this.stopForeground(true);
         } catch (Exception var4) {
            var4.printStackTrace();
         }
      } finally {
         ;
      }

   }

   protected void b() {
      synchronized(this){}

      Throwable var10000;
      label288: {
         AppLiveManager.NotificationFactory var1;
         boolean var10001;
         try {
            LiveLog.a(new String[]{"AbsLiveService", "startAmberForeground()"});
            var1 = AppLiveManager.getInstance().f();
         } catch (Throwable var33) {
            var10000 = var33;
            var10001 = false;
            break label288;
         }

         Object var2 = var1;
         if (var1 == null) {
            try {
               var2 = new DefaultNotificationFactory();
            } catch (Throwable var32) {
               var10000 = var32;
               var10001 = false;
               break label288;
            }
         }

         label275: {
            NotificationChannel var34;
            NotificationManager var3;
            try {
               var3 = (NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);
               var34 = new NotificationChannel(((AppLiveManager.NotificationFactory)var2).c(this), ((AppLiveManager.NotificationFactory)var2).b(this), ((AppLiveManager.NotificationFactory)var2).a(this));
            } catch (Throwable var31) {
               var10000 = var31;
               var10001 = false;
               break label288;
            }

            if (var3 != null) {
               try {
                  var34.setDescription("");
                  var34.setSound((Uri)null, (AudioAttributes)null);
                  var34.enableLights(true);
                  var34.setLightColor(-65536);
                  var34.setShowBadge(false);
                  var3.createNotificationChannel(var34);
               } catch (Throwable var30) {
                  var10000 = var30;
                  var10001 = false;
                  break label288;
               }
            }
         }

         label267:
         try {
            this.startForeground(((AppLiveManager.NotificationFactory)var2).d(this), ((AppLiveManager.NotificationFactory)var2).e(this));
         } catch (Throwable var29) {
            var10000 = var29;
            var10001 = false;
            break label267;
         }
      }
   }

   @Nullable
   public IBinder onBind(Intent var1) {
      return null;
   }

   public void onCreate() {
      super.onCreate();
   }

   public void onDestroy() {
      this.a();
      super.onDestroy();
   }

   public int onStartCommand(Intent var1, int var2, int var3) {
      this.b();
      return super.onStartCommand(var1, var2, var3);
   }

   public static class DefaultNotificationFactory implements AppLiveManager.NotificationFactory {
      public DefaultNotificationFactory() {
      }

      @RequiresApi(
              api = 24
      )
      public int a(Context var1) {
         return 1;
      }

      public String b(Context var1) {
         return "常驻消息";
      }

      public String c(Context var1) {
         return "permanent";
      }

      public int d(Context var1) {
         return 1001;
      }

      @RequiresApi(
              api = 26
      )
      public Notification e(Context var1) {
         return (new Notification.Builder(var1, this.c(var1))).setSmallIcon(R.mipmap.ic_launcher).setLargeIcon(BitmapFactory.decodeResource(var1.getResources(), R.mipmap.ic_launcher)).setContentTitle("APP常驻消息").setContentText("APP正在运行中。。。").setWhen(System.currentTimeMillis()).build();
      }
   }
}

