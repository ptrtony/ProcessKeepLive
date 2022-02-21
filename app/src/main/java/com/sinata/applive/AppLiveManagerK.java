package com.sinata.applive;


import android.content.Context;

public class AppLiveManagerK extends AppLiveManager {
    private static volatile KeepLiveManager h;

    public AppLiveManagerK() {
    }

    public static KeepLiveManager m(Context var0) {
        if (h == null) {
            synchronized(LiveServiceK.class){}

            Throwable var10000;
//            boolean var10001;
            label144: {
                try {
                    if (h == null) {
                        KeepLiveManager var1 = new KeepLiveManager(var0.getApplicationContext());
                        h = var1;
                        h.h();
                    }
                } catch (Throwable var13) {
                    var10000 = var13;
//                    var10001 = false;
                    break label144;
                }

                label141:
                try {
                    return h;
                } catch (Throwable var12) {
                    var10000 = var12;
//                    var10001 = false;
                    break label141;
                }
            }

            while(true) {
                Throwable var14 = var10000;

                try {
                    throw var14;
                } catch (Throwable var11) {
                    var10000 = var11;
//                    var10001 = false;
                    continue;
                }
            }
        } else {
            return h;
        }
    }

    public AppLiveManager b(String... var1) {
        LiveServiceK.i(getContext(), 1, var1);
        return this;
    }

    protected void i(Context var1) {
        this.a(new Class[]{LiveHelpFrontService.class});
    }

    protected void j() {
        LiveServiceK.i(this.getContext(), 0, (String[])null);
    }
}