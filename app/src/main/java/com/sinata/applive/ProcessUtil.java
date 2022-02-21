package com.sinata.applive;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public class ProcessUtil {
   private static volatile String a;

   static {
      Pattern.compile("[^:]+:([^:]+)");
   }

   public ProcessUtil() {
   }

   public static String a(@NonNull Context var0) {
      if (a == null) {
         if (Build.VERSION.SDK_INT >= 28) {
            String var2 = Application.getProcessName();
            a = var2;
            return var2;
         }

//         a = d();
//         if (!TextUtils.isEmpty(a)) {
//            return a;
//         }

         int var1 = Process.myPid();
         if (var1 <= 0) {
            return null;
         }

//         a = c(var1);
//         if (!TextUtils.isEmpty(a)) {
//            return a;
//         }

         a = b(var0, var1);
      }

      return a;
   }

   @Nullable
   private static String b(@NonNull Context var0, int var1) {
      ActivityManager var15 = (ActivityManager)var0.getSystemService(Context.ACTIVITY_SERVICE);

      List var16;
      boolean var10001;
      try {
         var16 = var15.getRunningAppProcesses();
      } catch (Throwable var14) {
         var10001 = false;
         return null;
      }

      if (var16 != null) {
         Iterator var17;
         try {
            var17 = var16.iterator();
         } catch (Throwable var13) {
            var10001 = false;
            return null;
         }

         try {
            while(var17.hasNext()) {
               ActivityManager.RunningAppProcessInfo var2 = (ActivityManager.RunningAppProcessInfo)var17.next();
               if (var2.pid == var1) {
                  String var18 = var2.processName;
                  return var18;
               }
            }
         } catch (Throwable var12) {
            var10001 = false;
            return null;
         }
      }

      return null;
   }

//   @Nullable
//   private static String c(int param0) {
//      // $FF: Couldn't be decompiled
//   }
//
//   @Nullable
//   private static String d() {
//      // $FF: Couldn't be decompiled
//   }

   public static boolean e(@NonNull Context var0) {
      String var1 = a(var0);
      boolean var2;
      if (!TextUtils.isEmpty(var1) && !TextUtils.equals(var0.getPackageName(), var1)) {
         var2 = false;
      } else {
         var2 = true;
      }

      return var2;
   }
}
