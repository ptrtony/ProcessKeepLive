package com.sinata.applive;

import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public abstract class AbsLiveService extends AbsFrontService {
   public AbsLiveService() {
   }

   @Nullable
   public IBinder onBind(Intent var1) {
      return null;
   }

   public void onCreate() {
      super.onCreate();
   }

   public void onDestroy() {
      super.onDestroy();
      LiveLog.a(new String[]{"AbsLiveService", "onDestroy()"});
      AppLiveManager.getInstance().k();
   }

   public int onStartCommand(Intent var1, int var2, int var3) {
      super.onStartCommand(var1, var2, var3);
      return START_STICKY;
   }
}