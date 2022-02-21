package com.sinata.applive;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;

public abstract class AbsInnerService extends Service {
   private int a = -1;

   public AbsInnerService() {
   }

   private void a() {
      int var1 = Build.VERSION.SDK_INT;
      if (var1 < 18) {
         this.startForeground(this.a, NotificationUtils.a());
      } else if (var1 < 25) {
         this.startForeground(this.a, NotificationUtils.b(this));
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
      this.stopForeground(true);
      super.onDestroy();
   }

   public int onStartCommand(Intent var1, int var2, int var3) {
      if (var1 != null && var1.hasExtra("key_id") && var1.getIntExtra("key_id", -1) != -1) {
         this.a = var1.getIntExtra("key_id", -1);
         this.a();
      }

      this.stopSelf();
      return super.onStartCommand(var1, var2, var3);
   }
}
