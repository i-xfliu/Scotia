package com.example.scotia.view.main.utils;

import android.widget.Toast;
import com.example.scotia.MyApplication;

/**
 * To show a toast
 */
public final class ToastUtils {

    private ToastUtils() {

    }

    public static void showLongToast(String msg) {
        Toast.makeText(MyApplication.getInstance(), msg, Toast.LENGTH_LONG).show();
    }

}
