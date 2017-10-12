package com.xiufeng.wedleairplane.utils;/**
 * Created by cyril on 16/3/4.
 */

import android.content.Context;
import android.widget.Toast;


import com.xiufeng.wedleairplane.R;


public class MethodUtils {


    //网络可用性检测
    public static boolean isNetworkToast(Context context) {
        if (!ValidateUtils.isNetworkAvailable(context)) {
            Toast.makeText(context, context.getResources().getString(R.string.network_is_not_available), Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
}