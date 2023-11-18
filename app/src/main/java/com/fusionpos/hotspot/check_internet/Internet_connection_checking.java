package com.fusionpos.hotspot.check_internet;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;

import androidx.fragment.app.FragmentActivity;


public class Internet_connection_checking {

    Activity activity;
    Context context;

    public Internet_connection_checking(FragmentActivity mactivity) {
        activity = mactivity;
    }

	/*public Internet_connection_checking(Context context) {
		context = context;
	}*/

    @SuppressLint("MissingPermission")
    public boolean checkInternetConnection() {

        ConnectivityManager conMgr = (ConnectivityManager)activity.getSystemService(Context.CONNECTIVITY_SERVICE);

        // ARE WE CONNECTED TO THE NET

        if (conMgr.getActiveNetworkInfo() != null

                && conMgr.getActiveNetworkInfo().isAvailable()

                && conMgr.getActiveNetworkInfo().isConnected()) {

            return true;

        } else {
            return false;

        }
    }
}
