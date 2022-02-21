package com.sinata.applive;

import android.content.Context;
import android.os.Build;

public class AppLiveManagerO extends AppLiveManager {
   private KeepLiveManager h;

   public AppLiveManagerO() {
   }

   public AppLiveManager b(String... var1) {
      this.h.c(var1);
      return this;
   }




   protected void i(Context var1) {
      this.h = new KeepLiveManager(var1);
      if (Build.VERSION.SDK_INT < 24) {
         LiveAlarmService.a(var1);
      }

   }

   protected void j() {
      if (ProcessUtil.e(getContext())) {
         this.h.h();
      }
   }
}
