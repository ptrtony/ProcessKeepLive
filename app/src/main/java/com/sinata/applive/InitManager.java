package com.sinata.applive;


import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class InitManager {
   private volatile boolean a;

   private InitManager() {
      this.a = false;
   }

   public static InitManager a() {
      return InitManager.Holder.a;
   }

   private void c() {
      try {
         Class var1 = InitManager.class.getClassLoader().loadClass("com.amber.lib.appusage.AppUseInfo");
         Method var2 = var1.getDeclaredMethod("getInstance");
         var2.setAccessible(true);
         Object var8 = var2.invoke((Object)null);
         Method var7 = var1.getDeclaredMethod("init");
         var7.setAccessible(true);
         var7.invoke(var8);
         Log.e("InitManager", "AppUseInfo init Success.");
      } catch (ClassNotFoundException var3) {
         var3.printStackTrace();
      } catch (NoSuchMethodException var4) {
         var4.printStackTrace();
      } catch (IllegalAccessException var5) {
         var5.printStackTrace();
      } catch (InvocationTargetException var6) {
         var6.printStackTrace();
      }

   }

   private void d() {
      try {
         Method var1 = InitManager.class.getClassLoader().loadClass("com.amber.lib.device.DeviceId").getDeclaredMethod("init");
         var1.setAccessible(true);
         var1.invoke((Object)null);
         Log.e("InitManager", "DeviceId init Success.");
      } catch (ClassNotFoundException var2) {
         var2.printStackTrace();
      } catch (NoSuchMethodException var3) {
         var3.printStackTrace();
      } catch (IllegalAccessException var4) {
         var4.printStackTrace();
      } catch (InvocationTargetException var5) {
         var5.printStackTrace();
      }

   }

   public void b() {
      if (!this.a) {
         synchronized(InitManager.class){
            try{
               if (this.a) {
                  return;
               }
               this.d();
               this.c();
               this.a = true;
            }catch (Exception e){
               e.printStackTrace();
            }
         }
      }
   }

   private static class Holder {
      private static final InitManager a = new InitManager();

      private Holder() {
      }
   }
}
