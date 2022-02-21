package com.sinata.applive;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

public abstract class AbsFrontService extends Service {
   private int a = 0;

   public AbsFrontService() {
   }

   private void d(Context var1) {
      synchronized(this){}

      Throwable var10000;
      label78: {
         boolean var10001;
         int var2;
         try {
            var2 = this.a;
         } catch (Throwable var8) {
            var10000 = var8;
            var10001 = false;
            break label78;
         }

         if (var2 != 0) {
            return;
         }

         try {
            this.a = ServiceNotificationIdManager.a().b(this.b(), var1);
         } catch (Throwable var7) {
            var10000 = var7;
            var10001 = false;
            break label78;
         }
         return;
      }
   }

   protected void a() {
      int var1 = Build.VERSION.SDK_INT;
      if (var1 < 18) {
         this.d(this);
         this.startForeground(this.a, NotificationUtils.a());
      } else if (var1 < 25) {
         this.d(this);
         this.startForeground(this.a, NotificationUtils.b(this));
         Intent var2 = new Intent(this, this.c());
         var2.putExtra("key_id", this.a);
         ServiceRunning.b(this, var2);
      }

   }

   protected String b() {
      return this.getClass().getSimpleName();
   }

   public abstract Class<? extends AbsInnerService> c();

   public void onCreate() {
      super.onCreate();
      if (GlobalConfig.getInstance().getGlobalContext() == null) {
         GlobalConfig.getInstance().init(this.getApplicationContext());
      }

      boolean var1 = false;
      if (Build.VERSION.SDK_INT < 25) {
         var1 = true;
      }

      LiveLog.a(new String[]{"AbsFrontService useLoopholeFront", String.valueOf(var1)});
      if (Build.VERSION.SDK_INT < 25) {
         this.a();
      }

   }
}

