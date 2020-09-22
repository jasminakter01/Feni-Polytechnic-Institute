package com.example.firebaseproject1;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;

public class DataBase_Test extends AppCompatActivity implements  View.OnClickListener{
EditText name,age;
Button savedata,loaddata;

DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base__test);
        name=findViewById(R.id.nameid);
        age=findViewById(R.id.ageid);
        savedata=findViewById(R.id.savedataid);
        loaddata=findViewById(R.id.loaddataid);
        loaddata.setOnClickListener(this);
        savedata.setOnClickListener(this);
        databaseReference= FirebaseDatabase.getInstance().getReference("Students");

    }

    @Override
    public void onClick(View v) {

        if (v.getId()==R.id.savedataid){
            String n=name.getText().toString().trim();
            String a=age.getText().toString().trim();
            String key=databaseReference.push().getKey();
            studentindfo ss=new studentindfo(n,a);
            databaseReference.child(key).setValue(ss);
            Toast.makeText(this, "Student Info Success", Toast.LENGTH_SHORT).show();
            name.setText("");
            age.setText("");
        } else if (v.getId()==R.id.loaddataid) {
            Intent intent=new Intent(DataBase_Test.this,Data_Load_Layout.class);
            startActivity(intent);}
    }




}


 class studentindfo{
    String name,age;
public studentindfo(){


}
    public studentindfo(String name, String age) {
        this.name = name;
        this.age = age;
    }

     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }

     public String getAge() {
         return age;
     }

     public void setAge(String age) {
         this.age = age;
     }
 }
