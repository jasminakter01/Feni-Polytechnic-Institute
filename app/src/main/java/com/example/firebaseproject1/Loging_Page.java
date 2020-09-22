package com.example.firebaseproject1;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Loging_Page extends AppCompatActivity {
EditText email,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loging__page);
        this.setTitle("Admin");
email=findViewById(R.id.firstemailid);
pass=findViewById(R.id.firstpassid);


    }

    public void loginclik(View view) {

        if (email.getText().toString().isEmpty()){
           email.setError("Please Enter Email");
        }else if (pass.getText().toString().isEmpty()){
            pass.setError("Please Enter Password");

        }else {
            if (!email.getText().toString().equals("fenisoft@gmail.com"))
            {
               email.setError("Email is Wrong");
            }else  if (!pass.getText().toString().equals("123456"))
            {
     pass.setError("PassWord is Wrong");
            }else {
        Intent intent=new Intent(getApplicationContext(),Admin_Home_Layout.class);
        startActivity(intent);
            }
        }
    }
}
