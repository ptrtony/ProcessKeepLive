package com.sinata.applive;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public abstract class AppsCompat {
   private static AppsCompat d;
   private static final Object e = new Object();
   private final List<String> a = new ArrayList();
   private PackageManager b;
   private List<String> c;
   AppsCompat(Context var1) {
      this.b = var1.getPackageManager();
      this.c = Arrays.asList(var1.getResources().getStringArray(R.array.self_package));
   }

   private void b(String var1) {
      synchronized(e){
         if (!this.a.contains(var1)) {
            this.a.add(var1);
         }
      }
   }

   public static AppsCompat d(){
      if (d == null){
         synchronized (AppsCompat.class){
            if (d == null){
               if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                  d =  new AppsCompatVL(GlobalConfig.getInstance().getGlobalContext());
               }else{
                  d = new AppsCompatV16(GlobalConfig.getInstance().getGlobalContext());
               }
            }
         }
      }
      return d;
   }

   private boolean e(String var1) {
      if (TextUtils.isEmpty(var1)) {
         return false;
      } else {
         List var2 = this.c;
         if (var2 != null) {
            Iterator var4 = var2.iterator();

            while(var4.hasNext()) {
               String var3 = (String)var4.next();
               if (!TextUtils.isEmpty(var3) && var1.startsWith(var3)) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   void a(String var1) {
      synchronized(e){
         try{
            if (this.a.contains(var1)) {
               return;
            }
            if (this.b == null) {
               return;
            }
            if (TextUtils.isEmpty(var1)) {
               return;
            }
            ApplicationInfo var3 = this.b.getApplicationInfo(var1, PackageManager.GET_META_DATA);
            if (var3 != null) {
               Bundle var22 = var3.metaData;
               if (var22 != null && var22.containsKey("AMBER_LIVE_APP")) {
                  this.b(var1);
                  return;
               }
            }
            if (this.e(var1)) {
               this.b(var1);
            }
         }catch (Exception e){
            e.printStackTrace();
         }
      }
   }

   public final List<String> c() {
      return c;
   }

   void f(String var1) {
      synchronized(e){
         try{
            if (this.a.contains(var1)) {
               this.a.remove(var1);
            }
         }catch (Exception e){
            e.printStackTrace();
         }
      }
   }
}
