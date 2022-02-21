package com.sinata.applive;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.PowerManager;
import android.text.TextUtils;

public class OnePiexlManager extends BroadcastReceiver implements Application.ActivityLifecycleCallbacks {
    private static final Object b = new Object();
    private static PowerManager c;
    private static OnePiexlManager d;
    private OnePiexlActivity a;

    private OnePiexlManager() {
    }

    static PowerManager a(Context var0) {
        if (c == null) {
            Object var1 = b;
            synchronized(var1){}

            Throwable var10000;
            boolean var10001;
            label144: {
                try {
                    if (c == null) {
                        c = (PowerManager)var0.getSystemService(Context.POWER_SERVICE);
                    }
                } catch (Throwable var13) {
                    var10000 = var13;
                    var10001 = false;
                    break label144;
                }

                label141:
                try {
                    return c;
                } catch (Throwable var12) {
                    var10000 = var12;
                    var10001 = false;
                    break label141;
                }
            }

            while(true) {
                Throwable var14 = var10000;

                try {
                    throw var14;
                } catch (Throwable var11) {
                    var10000 = var11;
                    var10001 = false;
                    continue;
                }
            }
        } else {
            return c;
        }
    }

    public static void d(Application var0) {
        synchronized(OnePiexlManager.class){}
        if (var0 == null) {
            ;
        } else {
            Throwable var10000;
            label148: {
                OnePiexlManager var1;
                boolean var10001;
                try {
                    var1 = d;
                } catch (Throwable var13) {
                    var10000 = var13;
                    var10001 = false;
                    break label148;
                }

                if (var1 != null) {
                    return;
                }

                PowerManager var16;
                try {
                    LiveLog.a(new String[]{"OnePiexlManager", "start"});
                    var1 = new OnePiexlManager();
                    d = var1;
                    IntentFilter var15 = new IntentFilter();
                    var15.addAction("android.intent.action.SCREEN_OFF");
                    var15.addAction("android.intent.action.SCREEN_ON");
                    var15.addAction("android.intent.action.USER_PRESENT");
                    var0.registerActivityLifecycleCallbacks(d);
                    var0.registerReceiver(d, var15);
                    var16 = a(var0);
                } catch (Throwable var12) {
                    var10000 = var12;
                    var10001 = false;
                    break label148;
                }

                if (var16 != null) {
                    try {
                        if (!var16.isScreenOn()) {
                            d.b(var0);
                        }
                    } catch (Throwable var11) {
                        var10000 = var11;
                        var10001 = false;
                        break label148;
                    }
                }

                return;
            }
        }
    }

    public void b(Context var1) {
        if (AppLiveManager.getInstance().h()) {
            LiveLog.a(new String[]{"OnePiexlManager", "ACTION_SCREEN_OFF"});
            Intent var2 = new Intent(var1, OnePiexlActivity.class);
            var2.addFlags(Intent.FLAG_RECEIVER_FOREGROUND);
            var2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            var1.startActivity(var2);
            LiveLog.a(new String[]{"OnePiexlActivity", "startActivity"});
        }
    }

    public void c(Context var1) {
        if (AppLiveManager.getInstance().h()) {
            LiveLog.a(new String[]{"OnePiexlManager", "ACTION_SCREEN_ON"});
            OnePiexlActivity var2 = this.a;
            if (var2 != null && !var2.isFinishing()) {
                var2.finish();
            }

        }
    }

    public void onActivityCreated(Activity var1, Bundle var2) {
        if (var1 instanceof OnePiexlActivity) {
            this.a = (OnePiexlActivity)var1;
            LiveLog.a(new String[]{"OnePiexlActivity", "onCreate by Callback"});
        }

    }

    public void onActivityDestroyed(Activity var1) {
        if (var1 instanceof OnePiexlActivity) {
            this.a = null;
            LiveLog.a(new String[]{"OnePiexlActivity", "onDestroyed by Callback"});
        }

    }

    public void onActivityPaused(Activity var1) {
    }

    public void onActivityResumed(Activity var1) {
        if (var1 instanceof OnePiexlActivity) {
            LiveLog.a(new String[]{"OnePiexlActivity", "onResumed by Callback"});
        }

    }

    public void onActivitySaveInstanceState(Activity var1, Bundle var2) {
    }

    public void onActivityStarted(Activity var1) {
    }

    public void onActivityStopped(Activity var1) {
    }

    public void onReceive(Context var1, Intent var2) {
        if (AppLiveManager.getInstance().h()) {
            if (TextUtils.equals(var2.getAction(), "android.intent.action.SCREEN_OFF")) {
                this.b(var1);
            } else if (TextUtils.equals(var2.getAction(), "android.intent.action.SCREEN_ON") || TextUtils.equals(var2.getAction(), "android.intent.action.USER_PRESENT")) {
                this.c(var1);
            }

        }
    }
}
