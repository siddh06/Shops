package com.example.fooddelivery.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fooddelivery.R;
import com.example.fooddelivery.Retrofit.RetrofitClient;
import com.example.fooddelivery.Util.AppPreference;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        Objects.requireNonNull(getSupportActionBar()).hide();

        TextView signUp = findViewById(R.id.signUpTxt);
        TextView userName = findViewById(R.id.txtUserName);
        TextView password = findViewById(R.id.txtPassword);
        Button btnLogin = findViewById(R.id.btnLogin);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginScreen.this, RegisterPage.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!userName.getText().toString().equalsIgnoreCase("") && !password.getText().toString().equalsIgnoreCase("")){
                    JsonObject object = new JsonObject();
                    AppPreference.getInstance(getApplicationContext()).putString("userName", userName.getText().toString());
                    object.addProperty("username", userName.getText().toString());
                    object.addProperty("password", password.getText().toString());
                    loginService(object);
                }
            }
        });

    }

    private void loginService(JsonObject loginObj){
        try {
            Call<Void> call = RetrofitClient.getInstance().getMyApi().login(loginObj);

            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                    Log.e("TAG", "onResponse: " );
                    if(response.isSuccessful()){
                        AppPreference.getInstance(getApplicationContext()).setBoolean("isLogin", true);
                        Intent intent=new Intent(LoginScreen.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else {
                        Toast.makeText(getApplicationContext(), "UserName And Password Not Match", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                    Log.e("TAG", "onFailure: " );
                }
            });
        }catch (Exception e){
            Log.e("LoginScreen", "loginService: "+ e.getMessage() );
        }
    }
}