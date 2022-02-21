package com.sinata.applive;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import java.util.Iterator;

@SuppressLint("SpecifyJobSchedulerIdRange")
@TargetApi(21)
class LiveJobService extends JobService {
   public LiveJobService() {
   }

   @TargetApi(21)
   private static JobInfo a(JobScheduler var0, int var1) {
      Iterator var2 = var0.getAllPendingJobs().iterator();

      JobInfo var3;
      do {
         if (!var2.hasNext()) {
            return null;
         }

         var3 = (JobInfo)var2.next();
      } while(var3 == null || var3.getId() != var1);

      return var3;
   }

   @SuppressLint("SpecifyJobSchedulerIdRange")
   public static void b(Context var0) {
      LiveLog.a(new String[]{"LiveJobService", "startLiveJobService()"});

      Exception var10000;
      label64: {
         JobScheduler var1;
         boolean var10001;
         try {
            var1 = (JobScheduler)var0.getSystemService(Context.JOB_SCHEDULER_SERVICE);
         } catch (Exception var9) {
            var10000 = var9;
            var10001 = false;
            break label64;
         }

         if (var1 == null) {
            return;
         }

         JobInfo var2;
         try {
            var2 = a(var1, 2122);
         } catch (Exception var8) {
            var10000 = var8;
            var10001 = false;
            break label64;
         }

         if (var2 != null) {
            try {
               var1.cancel(var2.getId());
            } catch (Exception var7) {
               var10000 = var7;
               var10001 = false;
               break label64;
            }
         }

         JobInfo.Builder var3;
         label65: {
            try {
               ComponentName var11 = new ComponentName(var0, LiveJobService.class);
               var3 = new JobInfo.Builder(2122, var11);
               if (Build.VERSION.SDK_INT >= 24) {
                  var3.setPeriodic(2000L, 2000L);
                  break label65;
               }
            } catch (Exception var6) {
               var10000 = var6;
               var10001 = false;
               break label64;
            }

            try {
               var3.setPeriodic(2000L);
            } catch (Exception var5) {
               var10000 = var5;
               var10001 = false;
               break label64;
            }
         }

         try {
            var3.setPersisted(true);
            var1.schedule(var3.build());
            LiveLog.a(new String[]{"LiveJobService", "startLiveJobService end()"});
            return;
         } catch (Exception var4) {
            var10000 = var4;
            var10001 = false;
         }
      }

      Exception var10 = var10000;
      var10.printStackTrace();
   }

   public void onCreate() {
      super.onCreate();
      LiveLog.a(new String[]{"LiveJobService", "onCreate()"});
   }

   public int onStartCommand(Intent var1, int var2, int var3) {
      LiveLog.a(new String[]{"LiveJobService", "onStartCommand()"});
      return super.onStartCommand(var1, var2, var3);
   }

   public boolean onStartJob(JobParameters var1) {
      LiveLog.a(new String[]{"LiveJobService", "onStartJob()"});
      if (GlobalConfig.getInstance().getGlobalContext() == null) {
         GlobalConfig.getInstance().init(this.getApplicationContext());
      }

      AppLiveManager.getInstance().l(0L);
      this.stopSelf();
      return false;
   }

   public boolean onStopJob(JobParameters var1) {
      LiveLog.a(new String[]{"LiveJobService", "onStopJob()"});
      return false;
   }
}
