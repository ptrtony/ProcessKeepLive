package com.sinata.applive;

import android.content.Context;

public class AppLiveManagerL extends AppLiveManager {
   private KeepLiveManager h;

   public AppLiveManagerL() {
   }

   public AppLiveManager b(String... var1) {
      this.h.c(var1);
      return this;
   }

   protected void i(Context var1) {
      this.h = new KeepLiveManager(var1);
      this.a(new Class[]{LiveServiceL.class});
   }

   protected void j() {
      LiveServiceL.e(this.getContext());
   }

   protected KeepLiveManager m() {
      return this.h;
   }
}

