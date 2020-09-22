package com.example.firebaseproject1;

import android.content.Context;
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

public class Student_Show_All_Detailse extends AppCompatActivity {
    RecyclerView recyclerView;
    StudentAllDAta_addapter studentAllDAta_addapter;
    List <Uploadstudent> uploadList;
    DatabaseReference databaseReference;
    ProgressBar progressBar;
    FirebaseStorage firebaseStorage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__show__all__detailse);

        recyclerView=findViewById(R.id.studenrecyid);
        recyclerView.setHasFixedSize(true);
        progressBar=findViewById(R.id.studenprogg);
        this.setTitle("Student Information");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        uploadList=new ArrayList<>();
        firebaseStorage=FirebaseStorage.getInstance();
        String im=getIntent().getStringExtra("id");
        String datbasename=getIntent().getStringExtra("datname");


        Query query= FirebaseDatabase.getInstance().getReference(datbasename)
                .orderByChild("id").equalTo(im);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    Uploadstudent uploadtest=dataSnapshot1.getValue(Uploadstudent.class);
                    uploadList.add(uploadtest);

                }
                studentAllDAta_addapter=new StudentAllDAta_addapter(getApplicationContext(),uploadList);
                recyclerView.setAdapter(studentAllDAta_addapter);





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







class StudentAllDAta_addapter extends
        RecyclerView.Adapter<StudentAllDAta_addapter.StudentAllDAta_ViewHolder>  {
    private Context context;
    private List<Uploadstudent> uploadlist;


    public StudentAllDAta_addapter(Context context, List<Uploadstudent> uploadlist) {
        this.context = context;
        this.uploadlist = uploadlist;
    }

    @NonNull
    @Override
    public StudentAllDAta_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view= layoutInflater.inflate(R.layout.student_all_item_layout,viewGroup,false);



        return new StudentAllDAta_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAllDAta_ViewHolder techer_viewHolder, int i) {
        Uploadstudent uploadtest=uploadlist.get(i);
        techer_viewHolder.name.setText("Name :"+uploadtest.getName());
        techer_viewHolder.roll.setText("Roll :"+uploadtest.getRoll());
        techer_viewHolder.reg.setText("Reg :"+uploadtest.getReg());
        techer_viewHolder.fathername.setText("Father Name :"+uploadtest.getFathername());
        techer_viewHolder.mothername.setText("Mother Name :"+uploadtest.getMothername());
        techer_viewHolder.dateofbirth.setText("Date Of Birth :"+uploadtest.getDateofbirth());
        techer_viewHolder.blood.setText("Blood :"+uploadtest.getBlood());
        techer_viewHolder.mobile.setText("Mobile No :"+uploadtest.getMobile());
        techer_viewHolder.email.setText("Email :"+uploadtest.getEmail());
        techer_viewHolder.department.setText("Department :"+uploadtest.getDepartment());
        techer_viewHolder.shift.setText("Shift :"+uploadtest.getShift());
        techer_viewHolder.ssion.setText("Session :"+uploadtest.getSsion());

         Picasso.with(context)
                .load(uploadtest.imageuri).
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


    public class StudentAllDAta_ViewHolder extends RecyclerView.ViewHolder  {
        ImageView imageView;
        TextView name,roll,reg,gen,fathername,mothername,mobile,email,blood,department,shift,ssion,dateofbirth;

         public StudentAllDAta_ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.studen_imageid);
            name=itemView.findViewById(R.id.studen_nameid);
            roll=itemView.findViewById(R.id.studen_rollid);
            reg=itemView.findViewById(R.id.studen_regid);
            fathername=itemView.findViewById(R.id.studen_fnameid);
            mothername=itemView.findViewById(R.id.studen_mnameid);
            mobile=itemView.findViewById(R.id.studen_mobile);
            email=itemView.findViewById(R.id.studen_emailid);
            blood=itemView.findViewById(R.id.studen_blood);
            department=itemView.findViewById(R.id.studen_departmentid);
            shift=itemView.findViewById(R.id.studen_shiftid);
            ssion=itemView.findViewById(R.id.studen_Sessionid);
            dateofbirth=itemView.findViewById(R.id.studen_dateofbid);

        }



    }


}
