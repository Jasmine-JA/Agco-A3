package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class SignupActivity extends AppCompatActivity {

    private ImageView backArrow;
    private EditText editTextFullName;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextConfirmPassword;
    private ImageView togglePasswordVisibility;
    private ImageView toggleConfirmPasswordVisibility;
    private AppCompatButton signupButton;
    private TextView loginLink;
    private LinearLayout googleButton;
    private LinearLayout facebookButton;

    private boolean isPasswordVisible = false;
    private boolean isConfirmPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        initViews();
        setClickListeners();
    }

    private void initViews() {
        backArrow = findViewById(R.id.backArrow);
        editTextFullName = findViewById(R.id.editTextFullName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        togglePasswordVisibility = findViewById(R.id.togglePasswordVisibility);
        toggleConfirmPasswordVisibility = findViewById(R.id.toggleConfirmPasswordVisibility);
        signupButton = findViewById(R.id.signupButton);
        loginLink = findViewById(R.id.loginLink);
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

        toggleConfirmPasswordVisibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConfirmPasswordVisible) {
                    editTextConfirmPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    toggleConfirmPasswordVisibility.setImageResource(R.drawable.ic_eye_hidden);
                } else {
                    editTextConfirmPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    toggleConfirmPasswordVisibility.setImageResource(R.drawable.ic_eye_visible);
                }
                isConfirmPasswordVisible = !isConfirmPasswordVisible;
                editTextConfirmPassword.setSelection(editTextConfirmPassword.getText().length());
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSignup();
            }
        });

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        googleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomToast("Google signup clicked");
            }
        });

        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomToast("Facebook signup clicked");
            }
        });
    }

    private void handleSignup() {
        String fullName = editTextFullName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String confirmPassword = editTextConfirmPassword.getText().toString().trim();

        editTextFullName.setError(null);
        editTextEmail.setError(null);
        editTextPassword.setError(null);
        editTextConfirmPassword.setError(null);

        if (fullName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            showCustomToast("Please fill in all fields");
            if (fullName.isEmpty()) {
                editTextFullName.requestFocus();
            } else if (email.isEmpty()) {
                editTextEmail.requestFocus();
            } else if (password.isEmpty()) {
                editTextPassword.requestFocus();
            } else {
                editTextConfirmPassword.requestFocus();
            }
            return;
        }

        if (fullName.length() < 2) {
            showCustomToast("Full name must be at least 2 characters");
            editTextFullName.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showCustomToast("Please enter a valid email address");
            editTextEmail.requestFocus();
            return;
        }

        if (password.length() < 6) {
            showCustomToast("Password must be at least 6 characters");
            editTextPassword.requestFocus();
            return;
        }

        if (!password.equals(confirmPassword)) {
            showCustomToast("Passwords do not match");
            editTextConfirmPassword.requestFocus();
            return;
        }

        showCustomToast("Account created successfully!");

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1500);
    }

    private void showCustomToast(String message) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast_layout,
                (ViewGroup) findViewById(R.id.toast_layout_root));

        TextView toastText = layout.findViewById(R.id.toast_text);
        toastText.setText(message);

        Toast customToast = new Toast(getApplicationContext());
        customToast.setGravity(Gravity.CENTER, 0, 0);
        customToast.setDuration(Toast.LENGTH_LONG);
        customToast.setView(layout);
        customToast.show();
    }
}