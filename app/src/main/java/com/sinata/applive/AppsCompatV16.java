package com.sinata.applive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import java.util.List;

public class AppsCompatV16 extends AppsCompat {
   private Context f;
   private PackageManager g;
   private AppsCompatV16.PackageMonitor h = new AppsCompatV16.PackageMonitor(this);

   AppsCompatV16(Context var1) {
      super(var1);
      this.f = var1;
      this.g();
   }

   private void g() {
      PackageManager var1 = this.f.getPackageManager();
      this.g = var1;
      int var2 = 0;

      for(List var4 = var1.getInstalledPackages(0); var2 < var4.size(); ++var2) {
         PackageInfo var3 = (PackageInfo)var4.get(var2);
         if ((var3.applicationInfo.flags & 1) == 0) {
            String var6 = var3.packageName;
            if (!TextUtils.isEmpty(var6)) {
               this.a(var6);
            }
         }
      }

      IntentFilter var5 = new IntentFilter("android.intent.action.PACKAGE_ADDED");
      var5.addAction("android.intent.action.PACKAGE_REMOVED");
      var5.addAction("android.intent.action.PACKAGE_CHANGED");
      var5.addDataScheme("package");
      this.f.registerReceiver(this.h, var5);
      var5 = new IntentFilter();
      var5.addAction("android.intent.action.EXTERNAL_APPLICATIONS_AVAILABLE");
      var5.addAction("android.intent.action.EXTERNAL_APPLICATIONS_UNAVAILABLE");
      this.f.registerReceiver(this.h, var5);
   }

   class PackageMonitor extends BroadcastReceiver {
      final AppsCompatV16 a;

      PackageMonitor(AppsCompatV16 var1) {
         this.a = var1;
      }

      public void onReceive(Context var1, Intent var2) {
         String var3 = var2.getAction();
         if (!"android.intent.action.PACKAGE_CHANGED".equals(var3) && !"android.intent.action.PACKAGE_REMOVED".equals(var3) && !"android.intent.action.PACKAGE_ADDED".equals(var3)) {
            if (!"android.intent.action.EXTERNAL_APPLICATIONS_AVAILABLE".equals(var3)) {
               "android.intent.action.EXTERNAL_APPLICATIONS_UNAVAILABLE".equals(var3);
            }
         } else {
            String var4;
            if (var2.getData() != null) {
               var4 = var2.getData().getSchemeSpecificPart();
            } else {
               var4 = null;
            }

            var2.getBooleanExtra("android.intent.extra.REPLACING", false);
            if (var4 != null && var4.length() != 0 && !"android.intent.action.PACKAGE_CHANGED".equals(var3)) {
               if ("android.intent.action.PACKAGE_REMOVED".equals(var3)) {
                  this.a.f(var4);
               } else if ("android.intent.action.PACKAGE_ADDED".equals(var3)) {
                  this.a.a(var4);
               }
            }
         }

      }
   }
}
