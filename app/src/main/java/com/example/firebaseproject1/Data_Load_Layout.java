package com.example.firebaseproject1;

import android.app.Activity;
import android.content.Context;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Data_Load_Layout extends AppCompatActivity {
ListView listView;
DatabaseReference databaseReference;
List<studentindfo>studentindfoList;
custom_addaptere custom_addaptereob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data__load__layout);
        listView=findViewById(R.id.listid);
        databaseReference= FirebaseDatabase.getInstance().getReference("Students");
studentindfoList=new ArrayList<>();
custom_addaptereob=new custom_addaptere(Data_Load_Layout.this,studentindfoList);


    }

    @Override
    protected void onStart() {
       databaseReference.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               studentindfoList.clear();
for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){

    studentindfo s=dataSnapshot1.getValue(studentindfo.class);
    studentindfoList.add(s);
}listView.setAdapter(custom_addaptereob);     }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });
        super.onStart();
    }
}








class custom_addaptere extends ArrayAdapter<studentindfo>{
private Activity context;
private List<studentindfo> studentindfoList;

     public custom_addaptere(Activity context, List<studentindfo> studentindfoList) {
         super(context, R.layout.data_load_desgin, studentindfoList);
         this.context = context;
         this.studentindfoList = studentindfoList;
     }




     @Override
     public View getView(int position, View convertView, ViewGroup parent){
         LayoutInflater layoutInflater=context.getLayoutInflater();
         View view=layoutInflater.inflate(R.layout.data_load_desgin,null,true);
         studentindfo ss=studentindfoList.get(position);
         TextView nam=view.findViewById(R.id.lnameid);
         TextView ag=view.findViewById(R.id.lageid);

nam.setText("Name :"+ss.getName());
         ag.setText("Age :"+ss.getAge());
         return view;
     }
 }
