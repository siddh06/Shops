package com.example.fooddelivery.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.fooddelivery.R;
import com.example.fooddelivery.Util.AppPreference;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TextView name = findViewById(R.id.name);
        TextView userName = findViewById(R.id.userName);
        TextView txtName = findViewById(R.id.txtName);
        TextView txtUserName = findViewById(R.id.txtUserName);
        TextView txtEmail = findViewById(R.id.txtEmail);

        String uName = AppPreference.getInstance(getApplicationContext()).getString("userName");
        name.setText(uName);
        txtName.setText(uName);

        String uEditName = "#"+uName+"123";
        userName.setText(uEditName);
        txtUserName.setText(uEditName);

        String mail = uName+"@gmail.com";
        txtEmail.setText(mail);

    }
}