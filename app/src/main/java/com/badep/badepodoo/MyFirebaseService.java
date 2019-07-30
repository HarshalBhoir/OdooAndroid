package com.badep.badepodoo;
import android.content.Context;
import android.util.Log;
import android.webkit.CookieManager;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseService extends FirebaseMessagingService {

    @Override
    public void onNewToken(String s) {
        String base_url = "https://" + getString(R.string.base_url);
        super.onNewToken(s);
        Log.e("newToken", s);
        CookieManager cookieManager = CookieManager.getInstance();
        String sessionCookie = cookieManager.getCookie(base_url);
        cookieManager.setCookie(base_url,"token=" + s);
        cookieManager.setCookie(base_url,"token_type=android");
        getSharedPreferences("_", MODE_PRIVATE).edit().putString("fb", s).apply();
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
    }

    public static String getToken(Context context) {
        return context.getSharedPreferences("_", MODE_PRIVATE).getString("fb", "empty");
    }
}