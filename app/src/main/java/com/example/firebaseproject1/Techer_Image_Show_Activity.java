package com.example.firebaseproject1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Techer_Image_Show_Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    Teacher_addapter teacher_addapter;
    List <Uploadtest> uploadList;
    DatabaseReference databaseReference;
    ProgressBar progressBar;
    FirebaseStorage firebaseStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_techer__image__show_);
        recyclerView=findViewById(R.id.teacher_recyid);
        recyclerView.setHasFixedSize(true);
        progressBar=findViewById(R.id.techprogg);
        this.setTitle("Employe Information");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        uploadList=new ArrayList<>();
        firebaseStorage=FirebaseStorage.getInstance();
        final String valu=getIntent().getStringExtra("depname");
    // databaseReference= FirebaseDatabase.getInstance().getReference("TecherDetailse");
      Query query=FirebaseDatabase.getInstance().getReference("TecherDetailse")
                .orderByChild("departmentName").equalTo(valu);

      query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    Uploadtest uploadtest=dataSnapshot1.getValue(Uploadtest.class);
                    uploadList.add(uploadtest);

                }
                teacher_addapter=new Teacher_addapter(Techer_Image_Show_Activity.this,uploadList);
                recyclerView.setAdapter(teacher_addapter);
        teacher_addapter.setOnItemClickLisener(new Teacher_addapter.onItemClickLisener() {
    @Override
    public void onItemClick(int position) {
        String id=uploadList.get(position).getid();

        Intent intent=new Intent(getApplicationContext(),Techer_alldetail_Show_Activity.class);
       intent.putExtra("id",id);
        startActivity(intent);
    }
});





                progressBar.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Error "+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);
            }
        });


    }
}






class Teacher_addapter extends RecyclerView.Adapter<Teacher_addapter.Techer_ViewHolder>  {
    private Context context;
    private List<Uploadtest> uploadlist;
private onItemClickLisener lisener;


    public Teacher_addapter(Context context, List<Uploadtest> uploadlist) {
        this.context = context;
        this.uploadlist = uploadlist;
    }

    @NonNull
    @Override
    public Techer_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
       View view= layoutInflater.inflate(R.layout.teacher_item_layout,viewGroup,false);



        return new Techer_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Techer_ViewHolder techer_viewHolder, int i) {
Uploadtest uploadtest=uploadlist.get(i);
techer_viewHolder.textView.setText(uploadtest.getImageName());
        Picasso.with(context)
                .load(uploadtest.imageUri).
                placeholder(R.mipmap.ic_launcher_round)
                .fit()
                .centerCrop()

                .into(techer_viewHolder
                        .imageView);

    }

    @Override
    public int getItemCount() {
        return uploadlist.size();
    }


    public class Techer_ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;
        ImageView imageView;

        public Techer_ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.teac_image);
            textView=itemView.findViewById(R.id.techer_name);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (lisener!=null){
                int position=getAdapterPosition();
                if (position!=RecyclerView.NO_POSITION){
                    lisener.onItemClick(position);
                }
            }
        }
    }
public interface  onItemClickLisener{
        void onItemClick(int position);
}

public void setOnItemClickLisener(onItemClickLisener lisener){
this.lisener=lisener;
}

}
