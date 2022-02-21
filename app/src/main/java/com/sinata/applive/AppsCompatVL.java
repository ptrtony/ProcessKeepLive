package com.sinata.applive;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.LauncherActivityInfo;
import android.content.pm.LauncherApps;
import android.content.pm.PackageManager;
import android.os.UserHandle;
import android.os.UserManager;
import android.text.TextUtils;

import java.util.Iterator;
import java.util.List;

@TargetApi(21)
public class AppsCompatVL extends AppsCompat {
   private Context f;
   private PackageManager g;
   private LauncherApps h;
   protected UserManager i;

   AppsCompatVL(Context var1) throws NullPointerException {
      super(var1);
      this.f = var1;
      this.g();
   }

   private void g() {
      this.g = this.f.getPackageManager();
      this.i = (UserManager)this.f.getSystemService(Context.USER_SERVICE);
      LauncherApps var1 = (LauncherApps)this.f.getSystemService(Context.LAUNCHER_APPS_SERVICE);
      this.h = var1;
      if (this.g != null) {
         UserManager var2 = this.i;
         if (var2 != null && var1 != null) {
            List var7 = var2.getUserProfiles();
            if (var7 == null) {
               throw new NullPointerException("users 为空");
            }

            Iterator var3 = var7.iterator();
            List var6 = null;

            while(var3.hasNext()) {
               UserHandle var9 = (UserHandle)var3.next();

               try {
                  var7 = this.h.getActivityList((String)null, var9);
               } catch (Exception var5) {
                  var5.printStackTrace();
                  var7 = var6;
               }

               var6 = var7;
               if (var7 != null) {
                  if (var7.isEmpty()) {
                     var6 = var7;
                  } else {
                     Iterator var4 = var7.iterator();

                     while(true) {
                        var6 = var7;
                        if (!var4.hasNext()) {
                           break;
                        }

                        LauncherActivityInfo var8 = (LauncherActivityInfo)var4.next();
                        if (var8 != null) {
                           ComponentName var10 = var8.getComponentName();
                           if (var10 != null) {
                              String var11 = var10.getPackageName();
                              if (!TextUtils.isEmpty(var11)) {
                                 this.a(var11);
                              }
                           }
                        }
                     }
                  }
               }
            }

            this.h.registerCallback(new LauncherApps.Callback() {
               final AppsCompatVL a;

               {
                  this.a = (AppsCompatVL) d();
               }

               public void onPackageAdded(String var1, UserHandle var2) {
                  this.a.a(var1);
               }

               public void onPackageChanged(String var1, UserHandle var2) {
               }

               public void onPackageRemoved(String var1, UserHandle var2) {
                  this.a.f(var1);
               }

               public void onPackagesAvailable(String[] var1, UserHandle var2, boolean var3) {
               }

               public void onPackagesUnavailable(String[] var1, UserHandle var2, boolean var3) {
               }
            });
            return;
         }
      }

   }
}
