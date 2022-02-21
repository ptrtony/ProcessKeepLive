package com.sinata.applive;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;

import java.util.Iterator;
import java.util.List;

public class ServiceRunning {
   public ServiceRunning() {
   }

   public static boolean a(Context var0, String var1) {
      if (var1 != null && var0 != null) {
         Exception var10000;
         label46: {
            boolean var10001;
            List var6;
            try {
               var6 = ((ActivityManager)var0.getSystemService(Context.ACTIVITY_SERVICE)).getRunningServices(2147483647);
            } catch (Exception var5) {
               var10000 = var5;
               var10001 = false;
               break label46;
            }

            if (var6 == null) {
               return false;
            }

            Iterator var7;
            try {
               if (var6.size() == 0) {
                  return false;
               }

               var7 = var6.iterator();
            } catch (Exception var4) {
               var10000 = var4;
               var10001 = false;
               break label46;
            }

            while(true) {
               boolean var2;
               try {
                  if (!var7.hasNext()) {
                     return false;
                  }

                  var2 = var1.equals(((ActivityManager.RunningServiceInfo)var7.next()).service.getClassName());
               } catch (Exception var3) {
                  var10000 = var3;
                  var10001 = false;
                  break;
               }

               if (var2) {
                  return true;
               }
            }
         }

         Exception var8 = var10000;
         var8.printStackTrace();
      }

      return false;
   }

   public static boolean b(Context var0, Intent var1) {
      if (var0 != null && var1 != null) {
         boolean var10001;
         AppLiveManager.ServiceLauncher var2;
         try {
            var2 = AppLiveManager.getInstance().g();
         } catch (Exception var6) {
            var10001 = false;
            return false;
         }

         label31: {
            if (var2 != null) {
               try {
                  if (var2.a(var1)) {
                     break label31;
                  }
               } catch (Exception var5) {
                  var10001 = false;
                  return false;
               }
            }

            try {
               var0.startService(var1);
            } catch (Exception var4) {
               var10001 = false;
               return false;
            }
         }

         try {
            return true;
         } catch (Exception var3) {
            var10001 = false;
         }
      }

      return false;
   }
}

