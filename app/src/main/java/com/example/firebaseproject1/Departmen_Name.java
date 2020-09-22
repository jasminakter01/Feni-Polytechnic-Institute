package com.example.firebaseproject1;

import android.content.Intent;

import android.os.Bundle;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Departmen_Name extends AppCompatActivity {
    CardView com,civil,electrical,arc,mecha,power;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departmen__name);
        com=findViewById(R.id.computerid);
        civil=findViewById(R.id.civillid);
        this.setTitle("Department Name");
        electrical=findViewById(R.id.electid);
        power=findViewById(R.id.powerid);
        mecha=findViewById(R.id.mechaid);
        arc=findViewById(R.id.archid);
        com.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  "contin","Student");
               String c= getIntent().getStringExtra("contin");
       if (c.equals("Student")){
           Intent intent=new Intent(getApplicationContext(),Student_Show_first_Lauout.class);
           intent.putExtra("depname","Computer");
           intent.putExtra("depnam","Coputer");

           startActivity(intent);
       }else if (c.equals("Employee")){
           Intent intent=new Intent(getApplicationContext(),Techer_Image_Show_Activity.class);
           intent.putExtra("depname","Computer");
           startActivity(intent);
       }


            }
        });
       civil.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String c= getIntent().getStringExtra("contin");
               if (c.equals("Student")){
                   Intent intent=new Intent(getApplicationContext(),Student_Show_first_Lauout.class);
                   intent.putExtra("depnam","Civil");
                   intent.putExtra("depname","Civil");
                   startActivity(intent);
               }
               else if (c.equals("Employee")){
                   Intent intent=new Intent(getApplicationContext(),Techer_Image_Show_Activity.class);
                   intent.putExtra("depname","Civil");

                   startActivity(intent);
               }

           }
       });
       electrical.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String c= getIntent().getStringExtra("contin");
               if (c.equals("Student")){
                   Intent intent=new Intent(getApplicationContext(),Student_Show_first_Lauout.class);
                   intent.putExtra("depname","Electrical");
                   intent.putExtra("depnam","Electrical");

                   startActivity(intent);}
               else if (c.equals("Employee")){
                   Intent intent=new Intent(getApplicationContext(),Techer_Image_Show_Activity.class);
                   intent.putExtra("depname","Electrical");

                   startActivity(intent);
               }


           }
       });
       power.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String c= getIntent().getStringExtra("contin");
               if (c.equals("Student")){
                   Intent intent=new Intent(getApplicationContext(),Student_Show_first_Lauout.class);
                   intent.putExtra("depname","Power");
                   intent.putExtra("depnam","Power");
                   startActivity(intent);
               }
               else if (c.equals("Employee")){
                   Intent intent=new Intent(getApplicationContext(),Techer_Image_Show_Activity.class);
                   intent.putExtra("depname","Power");

                   startActivity(intent);
               }

           }
       });
       mecha.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String c= getIntent().getStringExtra("contin");
               if (c.equals("Student")){
                   Intent intent=new Intent(getApplicationContext(),Student_Show_first_Lauout.class);
                   intent.putExtra("depname","Mecanical");
                   intent.putExtra("depnam","Mecanical");
                   startActivity(intent);
               }
               else if (c.equals("Employee")){
                   Intent intent=new Intent(getApplicationContext(),Techer_Image_Show_Activity.class);
                   intent.putExtra("depname","Mecanical");
                   startActivity(intent);
               }

           }
       });
       arc.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String c= getIntent().getStringExtra("contin");
               if (c.equals("Student")){

                   Intent intent=new Intent(getApplicationContext(),Student_Show_first_Lauout.class);
                   intent.putExtra("depname","AIDT");
                   intent.putExtra("depnam","AIDT");
                   startActivity(intent);
               }
               else if (c.equals("Employee")){

                   Intent intent=new Intent(getApplicationContext(),Techer_Image_Show_Activity.class);
                   intent.putExtra("depname","AIDT");
                   startActivity(intent);
               }

           }
       });
    }
}
