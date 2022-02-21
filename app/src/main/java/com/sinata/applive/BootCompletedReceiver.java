package com.sinata.applive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootCompletedReceiver extends BroadcastReceiver {
   public BootCompletedReceiver() {
   }

   public void onReceive(Context var1, Intent var2) {
      AppLiveManager.getInstance().l(0L);
   }
}
