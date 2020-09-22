package com.example.firebaseproject1;


import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Show_Image extends AppCompatActivity {
RecyclerView recyclerView;
MayAddapter mayAddapter;
List <Upload> uploadList;
DatabaseReference databaseReference;
ProgressBar progressBar;
FirebaseStorage firebaseStorage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__image);
      recyclerView=findViewById(R.id.recyclerviewid);
        progressBar=findViewById(R.id.p1);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        uploadList=new ArrayList<>();
        firebaseStorage=FirebaseStorage.getInstance();
       // databaseReference=FirebaseDatabase.getInstance().getReference("storageimage");
        Query query=FirebaseDatabase.getInstance().getReference("storageimage")
                .orderByChild("imagename").equalTo("hh");
query.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
uploadList.clear();
     for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){

         Upload upload=dataSnapshot1.getValue(Upload.class);
         upload.setKey(dataSnapshot1.getKey());
         uploadList.add(upload);
     }
        mayAddapter=new MayAddapter(Show_Image.this,uploadList);
        recyclerView.setAdapter(mayAddapter);
        mayAddapter.setOnclickLisener(new MayAddapter.OnItemClickLisener() {
            @Override
            public void OnItemClick(int position) {
                String text=uploadList.get(position).getImagename();
                Toast.makeText(Show_Image.this, ""+text+" is Selected "+position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void Ondoanydask(int position) {
                Toast.makeText(Show_Image.this, " do any task is Selected ", Toast.LENGTH_SHORT).show();
   }

            @Override
            public void Ondelete(int position) {
                Upload Seleceditem=uploadList.get(position);
              final String key=Seleceditem.getKey();
                StorageReference storageReference=firebaseStorage.getReferenceFromUrl(Seleceditem.getImageurl());
                storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
databaseReference.child(key).removeValue();
                    }
                });
            }
        });


        progressBar.setVisibility(View.INVISIBLE);
 }
    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {
        Toast.makeText(Show_Image.this, "Error "+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
   progressBar.setVisibility(View.INVISIBLE);
    }
});


    }
}

