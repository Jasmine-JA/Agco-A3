package com.example.myapplication;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class CustomToast {

    public static void showCustomToast(Context context, String message) {
        Toast toast = new Toast(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.custom_toast_layout, null);

        TextView text = layout.findViewById(R.id.toast_text);
        text.setText(message);

        toast.setView(layout);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0); // Center on screen

        toast.show();
    }

    public static void showSuccessToast(Context context, String message) {
        showCustomToast(context, message);
    }

    public static void showErrorToast(Context context, String message) {
        showCustomToast(context, message);
    }
}