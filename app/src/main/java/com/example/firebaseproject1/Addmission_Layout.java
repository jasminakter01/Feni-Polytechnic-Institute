package com.example.firebaseproject1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Addmission_Layout extends AppCompatActivity {
CardView s,e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmission__layout);
        s=findViewById(R.id.studentaddmissionid);
        e=findViewById(R.id.employeid);
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Student_Add_first_Lauout.class);
                startActivity(intent);
            }
        });
        e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Teacher_Add_Layout.class);
                startActivity(intent);
            }
        });

    }


}
