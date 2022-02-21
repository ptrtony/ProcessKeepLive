package com.sinata.workmanager;


import android.content.Context;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import java.util.concurrent.TimeUnit;

public class WorkTask {
   public void workTask(Context context){
      WorkRequest uploadWorkRequest = new PeriodicWorkRequest.Builder(UploadWorker.class,15, TimeUnit.MINUTES)
              .build();
      WorkManager.getInstance(context)
              .enqueue(uploadWorkRequest);


   }
}
