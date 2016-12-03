package pasqualini.android.utils.internet;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

public class InternetUtils {

    public enum EnumInternetType {WIFI, MOBILE, NONE}

    public static EnumInternetType checkNetworkConnection(@NonNull final Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeInfo = connMgr.getActiveNetworkInfo();
        if (activeInfo != null && activeInfo.isConnected()) {
            boolean wifiConnected = activeInfo.getType() == ConnectivityManager.TYPE_WIFI;
            boolean mobileConnected = activeInfo.getType() == ConnectivityManager.TYPE_MOBILE;
            if (wifiConnected) {
                return EnumInternetType.WIFI;
            } else if (mobileConnected) {
                return EnumInternetType.MOBILE;
            }
        }

        return EnumInternetType.NONE;
    }

    public static boolean hasInternetConnection(Context context) {
        return checkNetworkConnection(context) != EnumInternetType.NONE;
    }

    public static boolean hasWIFIConnection(Context context) {
        return checkNetworkConnection(context) == EnumInternetType.WIFI;
    }

}
