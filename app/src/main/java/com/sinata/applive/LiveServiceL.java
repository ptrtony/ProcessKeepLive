package com.sinata.applive;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class LiveServiceL extends AbsFrontService {
   public LiveServiceL() {
   }

   static void e(Context var0) {
      try {
         Intent var1 = new Intent(var0, LiveServiceL.class);
         var0.startService(var1);
      } catch (Exception var2) {
         var2.printStackTrace();
      }

   }

   public Class<? extends AbsInnerService> c() {
      return LiveServiceL.InnerService.class;
   }

   @Nullable
   public IBinder onBind(Intent var1) {
      return null;
   }

   public void onCreate() {
      super.onCreate();
      AppLiveManager var1 = AppLiveManager.getInstance();
      if (var1 instanceof AppLiveManagerL) {
         ((AppLiveManagerL)var1).m().h();
      }

   }

   public static class InnerService extends AbsInnerService {
      public InnerService() {
      }
   }
}
