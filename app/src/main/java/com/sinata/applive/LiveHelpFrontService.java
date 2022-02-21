package com.sinata.applive;

import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class LiveHelpFrontService extends AbsFrontService {
   public LiveHelpFrontService() {
   }

   public Class<? extends AbsInnerService> c() {
      return LiveHelpFrontService.InnerService.class;
   }

   @Nullable
   public IBinder onBind(Intent var1) {
      return null;
   }

   public static class InnerService extends AbsInnerService {
      public InnerService() {
      }
   }
}