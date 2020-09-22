package com.example.firebaseproject1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Create_Login_Data extends AppCompatActivity {
EditText newemail,oldemail,newpass,oldpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__login__data);
        newemail=findViewById(R.id.newemailid);
        oldemail=findViewById(R.id.oldemailid);
        newpass=findViewById(R.id.newpassid);
        oldpass=findViewById(R.id.oldpassid);




    }

    public void chancgepassemail(View view) {





    }
}
