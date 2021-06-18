package com.example.wears;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {
    @POST("auth/login")
    Call<com.example.wears.LoginResponse> loginUser(@Body com.example.wears.LoginRequest loginRequest);

    @POST("auth/register")
    Call<com.example.wears.RegisterResponse> registerUser(@Body com.example.wears.RegisterRequest registerRequest);
}
