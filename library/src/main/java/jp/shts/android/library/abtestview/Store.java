package jp.shts.android.library.abtestview;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

class Store {

    static void set(@NonNull Context context, @Nullable String keyname, @ABTestView.Which int which) {
        pref(context).edit().putInt(key(keyname), which).commit();
    }

    @ABTestView.Which
    static int get(@NonNull Context context, @Nullable String keyname) {
        return pref(context).getInt(key(keyname), ABTestView.Which.UNKNOWN);
    }

    @NonNull
    private static String prefix(@NonNull String content) {
        return "jp.shts.android.library.abtestview." + content;
    }

    @NonNull
    private static String key(@Nullable String keyname) {
        return prefix(TextUtils.isEmpty(keyname) ? "key" : keyname);
    }

    @NonNull
    private static SharedPreferences pref(@NonNull Context context) {
        return context.getSharedPreferences("which", Context.MODE_PRIVATE);
    }

}
