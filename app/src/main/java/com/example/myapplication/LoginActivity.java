package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class LoginActivity extends AppCompatActivity {

    private ImageView backArrow;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private ImageView togglePasswordVisibility;
    private CheckBox rememberMeCheckBox;
    private TextView forgotPassword;
    private AppCompatButton loginButton;
    private TextView signupLink;
    private LinearLayout googleButton;
    private LinearLayout facebookButton;

    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
        setClickListeners();
    }

    private void initViews() {
        backArrow = findViewById(R.id.backArrow);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        togglePasswordVisibility = findViewById(R.id.togglePasswordVisibility);
        rememberMeCheckBox = findViewById(R.id.checkBox);
        forgotPassword = findViewById(R.id.forgotPassword);
        loginButton = findViewById(R.id.loginButton);
        signupLink = findViewById(R.id.signupLink);
        googleButton = findViewById(R.id.googleButton);
        facebookButton = findViewById(R.id.facebookButton);
    }

    private void setClickListeners() {
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        togglePasswordVisibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPasswordVisible) {
                    editTextPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    togglePasswordVisibility.setImageResource(R.drawable.ic_eye_hidden);
                } else {
                    editTextPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    togglePasswordVisibility.setImageResource(R.drawable.ic_eye_visible);
                }
                isPasswordVisible = !isPasswordVisible;
                editTextPassword.setSelection(editTextPassword.getText().length());
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomToast("Forgot password clicked");
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleLogin();
            }
        });

        signupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
                finish();
            }
        });

        googleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomToast("Google login clicked");
            }
        });

        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomToast("Facebook login clicked");
            }
        });
    }

    private void handleLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        boolean rememberMe = rememberMeCheckBox.isChecked();

        // Clear any previous errors
        editTextEmail.setError(null);
        editTextPassword.setError(null);

        // Check if any field is empty first
        if (email.isEmpty() || password.isEmpty()) {
            showCustomToast("Please fill in all fields");
            if (email.isEmpty()) {
                editTextEmail.requestFocus();
            } else {
                editTextPassword.requestFocus();
            }
            return;
        }

        // Validate email format
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showCustomToast("Please enter a valid email address");
            editTextEmail.requestFocus();
            return;
        }

        // Validate password length
        if (password.length() < 6) {
            showCustomToast("Password must be at least 6 characters");
            editTextPassword.requestFocus();
            return;
        }

        // If all validations pass
        showCustomToast("Login successful!");
    }

    private void showCustomToast(String message) {
        // Inflate the custom toast layout
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast_layout,
                (ViewGroup) findViewById(R.id.toast_layout_root));

        // Set the custom message
        TextView toastText = layout.findViewById(R.id.toast_text);
        toastText.setText(message);

        // Create and configure the toast
        Toast customToast = new Toast(getApplicationContext());
        customToast.setGravity(Gravity.CENTER, 0, 0); // Center on screen
        customToast.setDuration(Toast.LENGTH_LONG); // Show for a longer duration
        customToast.setView(layout);
        customToast.show();
    }
}