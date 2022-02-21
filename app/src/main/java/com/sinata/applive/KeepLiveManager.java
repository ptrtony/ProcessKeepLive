package com.sinata.applive;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class KeepLiveManager {
   private HandlerThread a = new HandlerThread("applive-check-live");
   private Handler b = null;
   private volatile boolean c = false;
   private final List<String> d = new ArrayList();
   private final ReadWriteLock e = new ReentrantReadWriteLock();
   private final List<AppLiveManager.Checker> f = new ArrayList();
   private final ReadWriteLock g = new ReentrantReadWriteLock();
   private Context h;

   public KeepLiveManager(Context var1) {
      this.h = var1.getApplicationContext();
      this.a.start();
      this.b = new Handler(this.a.getLooper()) {

         public void handleMessage(Message var1) {
            super.handleMessage(var1);
            if (var1 != null) {
               if (Calendar.getInstance().get(11) >= 7) {
                  Calendar.getInstance().get(11);
               }

               if (var1.what == 100) {
                  e();
                  this.removeMessages(100);
                  this.sendEmptyMessageDelayed(100, 30000L);
               }

               if (var1.what == 101) {
                  d();
                  this.removeMessages(101);
                  this.sendEmptyMessageDelayed(101, 150000L);
               }

            }
         }
      };
   }

   private void d() {
      ContentResolver var1 = this.h.getContentResolver();
      Iterator var2 = AppsCompat.d().c().iterator();

      while(var2.hasNext()) {
         String var3 = (String)var2.next();
         StringBuilder var4 = new StringBuilder();
         var4.append("content://");
         var4.append(var3);
         var4.append(".applive.WakeProvider");
         Uri var6 = Uri.parse(var4.toString());

         try {
            var1.query(var6, (String[])null, (String)null, (String[])null, (String)null, (CancellationSignal)null);
         } catch (Exception var5) {
            var5.printStackTrace();
         }
      }

   }

   private void e() {
      LiveLog.a(new String[]{"KeepLiveManager", "checkService()"});
      this.e.readLock().lock();
      Iterator var1 = this.d.iterator();

      while(var1.hasNext()) {
         String var2 = (String)var1.next();
         if (!TextUtils.isEmpty(var2)) {
            boolean var3 = ServiceRunning.a(this.h, var2);
            LiveLog.a(new String[]{"name ", var2});
            StringBuilder var4 = new StringBuilder();
            var4.append("run  ");
            var4.append(var3);
            LiveLog.a(new String[]{var4.toString()});
            if (!var3) {
               Intent var6 = new Intent();
               var6.setClassName(this.h.getPackageName(), var2);
               LiveLog.a(new String[]{"start", String.valueOf(ServiceRunning.b(this.h, var6))});
            }
         }
      }

      this.e.readLock().unlock();
      this.g.readLock().lock();
      Iterator var5 = this.f.iterator();

      while(var5.hasNext()) {
         ((AppLiveManager.Checker)var5.next()).a(this.h);
      }
      this.g.readLock().unlock();
   }

   public void c(String... var1) {
      LiveLog.a(new String[]{"KeepLiveManager", "addService()"});
      if (var1 != null && var1.length != 0) {
         this.e.writeLock().lock();
         int var2 = var1.length;

         for(int var3 = 0; var3 < var2; ++var3) {
            String var4 = var1[var3];
            if (!this.d.contains(var4)) {
               this.d.add(var4);
            }
         }

         this.e.writeLock().unlock();
      }

   }

   public void f(String... var1) {
      LiveLog.a(new String[]{"KeepLiveManager", "removeService()"});
      if (var1 != null && var1.length != 0) {
         this.e.writeLock().lock();
         int var2 = var1.length;

         for(int var3 = 0; var3 < var2; ++var3) {
            String var4 = var1[var3];
            if (this.d.contains(var4)) {
               this.d.remove(var4);
            }
         }

         this.e.writeLock().unlock();
      }

   }

   public void g() {
      this.b.sendEmptyMessage(100);
   }

   public void h() {
      if (!this.c) {
         synchronized(KeepLiveManager.class){
            try {
               if (!this.c) {
                  this.c = true;
                  this.b.sendEmptyMessage(100);
                  this.b.sendEmptyMessage(101);
               }
            }catch (Exception e){
               e.printStackTrace();
            }
         }
      }
   }
}
