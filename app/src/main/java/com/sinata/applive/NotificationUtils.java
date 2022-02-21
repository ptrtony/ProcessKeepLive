package com.sinata.applive;

import android.app.Notification;
import android.content.Context;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class NotificationUtils {
   public NotificationUtils() {
   }

   public static Notification a() {
      return new Notification();
   }

   @RequiresApi(api = 16)
   public static Notification b(Context var0) {
      NotificationCompat.Builder var1 = new NotificationCompat.Builder(var0);
      var1.setSmallIcon(R.mipmap.ic_launcher);
      var1.setContentInfo("");
      var1.setContentTitle("");
      return var1.build();
   }
}
