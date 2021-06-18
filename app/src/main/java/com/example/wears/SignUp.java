package com.example.wears;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignUp extends AppCompatActivity {
    Button btnSignUp;
    EditText edUsername, edPassword, edFirstname, edSecondName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        btnSignUp = findViewById(R.id.button);
        edUsername = findViewById(R.id.username2);
        edPassword = findViewById(R.id.password2);
        edFirstname = findViewById(R.id.firstname);
        edSecondName = findViewById(R.id.secondname);


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edUsername.getText().toString()) || TextUtils.isEmpty(edFirstname.getText().toString()) || TextUtils.isEmpty(edSecondName.getText().toString()) || TextUtils.isEmpty(edPassword.getText().toString())) {
                    Toast.makeText(com.example.wears.SignUp.this, "Поля пустые", Toast.LENGTH_LONG).show();
                } else {
                    com.example.wears.RegisterRequest registerRequest = new com.example.wears.RegisterRequest();
                    registerRequest.setEmail(edUsername.getText().toString());
                    registerRequest.setFirstName(edFirstname.getText().toString());
                    registerRequest.setLastName(edSecondName.getText().toString());
                    registerRequest.setPassword(edPassword.getText().toString());

                    SignUpUsers(registerRequest);
                }
            }
        });
    }

    public void SignUpUsers(com.example.wears.RegisterRequest registerRequest) {
        Call<com.example.wears.RegisterResponse> registerResponseCall = com.example.wears.ApiClient.getService().registerUser(registerRequest);
        registerResponseCall.enqueue(new Callback<com.example.wears.RegisterResponse>() {
            @Override
            public void onResponse(Call<com.example.wears.RegisterResponse> call, Response<com.example.wears.RegisterResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(com.example.wears.SignUp.this, "Successful", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(com.example.wears.SignUp.this, SIgnIn.class));
                    finish();
                } else {
                    Toast.makeText(com.example.wears.SignUp.this, "Возникла ощибка, повторите позже...", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<com.example.wears.RegisterResponse> call, Throwable t) {
                Toast.makeText(com.example.wears.SignUp.this, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void init() {
        btnSignUp = findViewById(R.id.button);
        edUsername = findViewById(R.id.username2);
        edPassword = findViewById(R.id.password2);
        edFirstname = findViewById(R.id.firstname);
        edSecondName = findViewById(R.id.secondname);
    }
}
