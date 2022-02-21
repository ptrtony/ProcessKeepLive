package com.sinata.applive;

import android.content.Context;
import android.os.Process;
import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

public class ServiceNotificationIdManager {
   private static volatile ServiceNotificationIdManager b;
   private static Map<String, Integer> c;
   private int a;

   private ServiceNotificationIdManager() {
      int var1 = Process.myPid();
      if (2147483 < var1) {
         this.a = var1;
      } else {
         this.a = var1 * 1000;
      }

      this.a = 21;
      c = new HashMap();
   }

   public static ServiceNotificationIdManager a() {
      if (b == null) {
         synchronized(ServiceNotificationIdManager.class){}

         Throwable var10000;
         boolean var10001;
         label144: {
            try {
               if (b == null) {
                  ServiceNotificationIdManager var0 = new ServiceNotificationIdManager();
                  b = var0;
               }
            } catch (Throwable var12) {
               var10000 = var12;
               var10001 = false;
               break label144;
            }

            label141:
            try {
               return b;
            } catch (Throwable var11) {
               var10000 = var11;
               var10001 = false;
               break label141;
            }
         }

         while(true) {
            Throwable var13 = var10000;

            try {
               throw var13;
            } catch (Throwable var10) {
               var10000 = var10;
               var10001 = false;
               continue;
            }
         }
      } else {
         return b;
      }
   }

   public int b(String var1, Context var2) {
      synchronized(this){
         boolean var3 = false;
         try {
            var3 = TextUtils.isEmpty(var1);
         }catch (Exception e){

         }
         if (var3) {
            return 0;
         }
         int var4;
         if (c.containsKey(var1)) {
            var4 = c.get(var1);
            return var4;
         }
         var4 = this.a + 1;
         this.a = var4;
         c.put(var1, var4);
         var4 = this.a;
         return var4;
      }
   }
}

