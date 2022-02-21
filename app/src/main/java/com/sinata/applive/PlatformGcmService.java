package com.sinata.applive;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

/**
 * Title:
 * Description:
 * Copyright:Copyright(c)2021
 * Company:成都博智维讯信息技术股份有限公司
 *
 * @author jingqiang.cheng
 * @date 2022/2/20
 */
public class PlatformGcmService extends Service {
   public PlatformGcmService() {
   }

   @Nullable
   public IBinder onBind(Intent var1) {
      return null;
   }

   public void onCreate() {
      super.onCreate();
      if (GlobalConfig.getInstance().getGlobalContext() == null) {
         GlobalConfig.getInstance().init(this.getApplicationContext());
      }

      LiveLog.a(new String[]{"PlatformGcmService", "create"});
   }

   public void onDestroy() {
      super.onDestroy();
   }

   public int onStartCommand(Intent var1, int var2, int var3) {
      AppLiveManager.getInstance().k();
      return super.onStartCommand(var1, var2, var3);
   }
}

