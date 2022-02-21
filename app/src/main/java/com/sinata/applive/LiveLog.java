package com.sinata.applive;


public class LiveLog {
   public LiveLog() {
   }

   public static void a(String... var0) {
      if (var0 != null && var0.length != 0) {
         StringBuilder var1 = new StringBuilder();
         int var2 = var0.length;

         for(int var3 = 0; var3 < var2; ++var3) {
            var1.append(var0[var3]);
            var1.append(" ==> ");
         }

         var1.toString();
      }

   }
}

