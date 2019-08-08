package com.hanssoncompany.mr_hwang;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getCanonicalName();
    private FirebaseAuth mAuth;
    TextInputEditText etEmail, etPW;
    RelativeLayout RelativeLayout_login;
    String emailOK = "123@gmail.com";
    String passwordOK = "1234";

    String inputEmail = "";
    String inputPassword = "";

    //{"key":"value","key":"value"}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        etEmail = findViewById(R.id.TextInputEditText_email);
        etPW = findViewById(R.id.TextInputEditText_password);
        RelativeLayout_login = findViewById(R.id.RelativeLayout_login);

        Button btnSignUp = findViewById(R.id.btn_signup);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EmailPasswordActivity.class);
                startActivity(intent);
            }



        });


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        //1. 값을 가져온다 - 검사 (123@gmail.com / 1234)
        //2. 클릭을 감지한다
        //3. 1번의 값을 다음 액티비티로 넘긴다
        RelativeLayout_login.setClickable(true);
        etEmail.addTextChangedListener(new

                                               TextWatcher() {
                                                   @Override
                                                   public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                                   }

                                                   @Override
                                                   public void onTextChanged(CharSequence s, int start, int before, int count) {

                                                       if (s != null) {
                                                           Log.d("SENTI", s.toString());
                                                           inputEmail = s.toString();
                                                           RelativeLayout_login.setEnabled(validation());
                                                       }


                                                   }

                                                   @Override
                                                   public void afterTextChanged(Editable s) {

                                                   }
                                               });

        etPW.addTextChangedListener(new

                                            TextWatcher() {
                                                @Override
                                                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                                }

                                                @Override
                                                public void onTextChanged(CharSequence s, int start, int before, int count) {

                                                    if (s != null) {
                                                        Log.d("SENTI", s.toString());
                                                        inputPassword = s.toString();
                                                        RelativeLayout_login.setEnabled(validation());
                                                    }

                                                }

                                                @Override
                                                public void afterTextChanged(Editable s) {

                                                }
                                            });

//        RelativeLayout_login.setClickable(true);
        RelativeLayout_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("SENTI", "onClick");

                String email = etEmail.getText().toString();
                String password = etPW.getText().toString();

                Intent intent = new Intent(MainActivity.this, LoginResultActivity.class);
                intent.putExtra("email", email);
                intent.putExtra("password", password);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser user) {

    }

    public boolean validation() {
        Log.d("SENTI", inputEmail + " / " + inputPassword + " / " + (inputEmail.equals(emailOK)) + " / " + (inputPassword.equals(passwordOK)));
        return inputEmail.equals(emailOK) && inputPassword.equals(passwordOK);
    }
}
