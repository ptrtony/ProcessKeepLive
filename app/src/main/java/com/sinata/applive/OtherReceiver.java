package com.sinata.applive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class OtherReceiver extends BroadcastReceiver {
   public OtherReceiver() {
   }

   public void onReceive(Context var1, Intent var2) {
      if (GlobalConfig.getInstance().getGlobalContext() == null) {
         GlobalConfig.getInstance().init(var1.getApplicationContext());
      }

      AppLiveManager.getInstance().k();
   }
}
