package com.example.fooddelivery.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fooddelivery.R;

public class RegisterPage extends AppCompatActivity {

    EditText userName, email, mobileNumber, password;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        getSupportActionBar().hide();

        userName = findViewById(R.id.edtxtUserName);
        email = findViewById(R.id.edtxtEmail);
        mobileNumber = findViewById(R.id.edtxtMobile);
        password = findViewById(R.id.edtxtPassword);
        btnRegister = findViewById(R.id.btnregister);
        
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerNewUser();
            }
        });
    }

    private void registerNewUser() {
        String userEmail = email.getText().toString();
        String userPass = password.getText().toString();
        String mobile_no = mobileNumber.getText().toString();
        String user_name = userName.getText().toString();

        if(userEmail.isEmpty() || userEmail.equalsIgnoreCase("") || !userEmail.endsWith("@gmail.com")){
            Toast.makeText(this, "Please Enter Valid Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (userPass.isEmpty() || userPass.equalsIgnoreCase("")) {
            Toast.makeText(this, "Enter PassWord", Toast.LENGTH_SHORT).show();
            return;
        }
        if(userPass.length() > 6){
            Toast.makeText(this, "PassWord should less Than 6 charachter", Toast.LENGTH_SHORT).show();
            return;
        }
        if(mobileNumber.length() == 10){
            Toast.makeText(this, "Register Successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RegisterPage.this, LoginScreen.class);
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(this, "Enter Valid Number"+mobileNumber.toString().length(), Toast.LENGTH_SHORT).show();
        }
    }
}