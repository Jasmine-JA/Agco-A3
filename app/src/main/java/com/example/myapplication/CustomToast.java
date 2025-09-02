package com.example.myapplication;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class CustomToast {

    public static void showCustomToast(Context context, String message) {
        // Create custom toast
        Toast toast = new Toast(context);

        // Inflate custom layout
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.custom_toast_layout, null);

        // Set the message
        TextView text = layout.findViewById(R.id.toast_text);
        text.setText(message);

        // Configure toast
        toast.setView(layout);
        toast.setDuration(Toast.LENGTH_LONG); // Show for about 3.5 seconds
        toast.setGravity(Gravity.CENTER, 0, 0); // Center on screen

        // Show the toast
        toast.show();
    }

    public static void showSuccessToast(Context context, String message) {
        showCustomToast(context, message);
    }

    public static void showErrorToast(Context context, String message) {
        showCustomToast(context, message);
    }
}