package com.example.firebaseproject1;

import android.os.Bundle;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Show_Image2 extends AppCompatActivity {
RecyclerView recyclerView;
MayAddapter2 mayAddapter;
List <Upload2> uploadList;
DatabaseReference databaseReference;
ProgressBar progressBar;
FirebaseStorage firebaseStorage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__image2);
      recyclerView=findViewById(R.id.recyclerviewid2);
        progressBar=findViewById(R.id.p2);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        uploadList=new ArrayList<>();
        firebaseStorage=FirebaseStorage.getInstance();
        databaseReference=FirebaseDatabase.getInstance().getReference("storageimagee");

databaseReference.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
uploadList.clear();
     for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){

         Upload2 upload=dataSnapshot1.getValue(Upload2.class);
         upload.setKey(dataSnapshot1.getKey());
         uploadList.add(upload);
     }
        mayAddapter=new MayAddapter2(Show_Image2.this,uploadList);
        recyclerView.setAdapter(mayAddapter);
        mayAddapter.setOnclickLisener(new MayAddapter2.OnItemClickLisener() {
            @Override
            public void OnItemClick(int position) {
                String text2=uploadList.get(position).getImagename();
                String text22=uploadList.get(position).getImagename2();

                Toast.makeText(Show_Image2.this, ""+text2+"ggggggggg"+text22+" is Selected "+position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void Ondoanydask(int position) {
                Toast.makeText(Show_Image2.this, " do any task is Selected ", Toast.LENGTH_SHORT).show();
}

            @Override
            public void Ondelete(int position) {
                Upload2 Seleceditem=uploadList.get(position);
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
        Toast.makeText(Show_Image2.this, "Error "+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
   progressBar.setVisibility(View.INVISIBLE);
    }
});


    }
}

