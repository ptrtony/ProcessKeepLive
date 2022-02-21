package com.sinata.applive;

import android.app.Activity;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.Window;
import android.view.WindowManager;

public final class OnePiexlActivity extends Activity {
   public OnePiexlActivity() {
   }

   private void a() {
      PowerManager var1 = OnePiexlManager.a(this);
      if (var1 != null && var1.isScreenOn()) {
         this.finish();
      }

   }

   public void finish() {
      if (!this.isFinishing()) {
         super.finish();
      }

      LiveLog.a(new String[]{"OnePiexlActivity", "finish()"});
   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      LiveLog.a(new String[]{"OnePiexlActivity", "onCreate()"});
      Window var3 = this.getWindow();
      var3.setGravity(51);
      var3.addFlags(524288);
      WindowManager.LayoutParams var2 = var3.getAttributes();
      var2.x = 0;
      var2.y = 0;
      var2.height = 1;
      var2.width = 1;
      var3.setAttributes(var2);
      this.a();
   }

   protected void onResume() {
      super.onResume();
      this.a();
   }
}
