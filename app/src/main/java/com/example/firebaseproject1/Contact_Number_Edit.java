package com.example.firebaseproject1;

import android.app.Activity;
import android.content.Context;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Contact_Number_Edit extends AppCompatActivity {
Spinner spinner;
EditText editText1,editText2;
    ArrayAdapter<String> spinneradepter;
    private FirebaseAuth mAuth;
DatabaseReference databaseReference;

    ListView listView;
    List<Uploadcontact>uploadcontactList;
   contoct_addaptere custom_addaptereob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact__number__edit);
        spinner = findViewById(R.id.titeleditid);
        editText1 = findViewById(R.id.connameedit);
     editText2=findViewById(R.id.connumberedid);
        final String[] dep = getResources().getStringArray(R.array.titel);
        spinneradepter = new ArrayAdapter<String>(getApplicationContext(), R.layout.ssesion_layout, R.id.textviewids, dep);
        spinner.setAdapter(spinneradepter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String dd=dep[position];
                String s;
                s=spinner.getSelectedItem().toString();
                Query query=FirebaseDatabase.getInstance().getReference("Imergency_Number")
                        .orderByChild("title").equalTo(s);

                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                             Uploadcontact uploadtest=dataSnapshot1.getValue(Uploadcontact.class);
                           uploadcontactList.add(uploadtest);


                        }
/*
                    Uploadcontact uploadtest=uploadcontactList.get(s);
                    editText1.setText("Name :"+uploadtest.getName());
*/

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(Contact_Number_Edit.this, "Error "+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    public void contacedit(View view) {



    }
}

class contoct_addaptere extends ArrayAdapter<Uploadcontact>{
    private Activity context;
    private List<Uploadcontact> uploadcontactList;

    public contoct_addaptere(Activity context, List<Uploadcontact> uploadcontactList) {
        super(context, R.layout.contact_detail_show, uploadcontactList);
        this.context = context;
        this.uploadcontactList = uploadcontactList;
    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater layoutInflater=context.getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.contact_detail_show,null,true);
        Uploadcontact ss=uploadcontactList.get(position);
        TextView nam=view.findViewById(R.id.conname);
        TextView num=view.findViewById(R.id.connumber);

        nam.setText("Name :"+ss.getName());
        num.setText("Age :"+ss.getNumber());
        return view;
    }
}
