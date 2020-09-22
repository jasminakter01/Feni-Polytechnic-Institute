package com.example.firebaseproject1;

import android.content.Intent;

import android.os.Bundle;

import android.view.View;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Admin_Home_Layout extends AppCompatActivity {
CardView addcon,technology,student,employe,notice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__home__layout);
        addcon=findViewById(R.id.contact_number_id);
        this.setTitle("Admin Panel");

        addcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Contact_Numbeer.class);
                startActivity(intent);
            }
        });
        technology=findViewById(R.id.technologyid);
        technology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Technology_Add.class
                );
                startActivity(intent);
            }
        });
        student=findViewById(R.id.studentidd);
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Student_Add_first_Lauout.class
                );
                startActivity(intent);
            }
        });
        employe=findViewById(R.id.employeidd);
        employe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Teacher_Add_Layout.class
                );
                startActivity(intent);
            }
        });
notice=findViewById(R.id.noticeidd);
notice.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(getApplicationContext(),Notice_Activity.class);
        startActivity(intent);
    }
});

    }
}
