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

public class Techer_alldetail_Show_Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    Teacherdeta_addapter teacherd_addapter;
    List <Uploadtest> uploadList;
    DatabaseReference databaseReference;
    ProgressBar progressBar;
    FirebaseStorage firebaseStorage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_techer__alldetail__show_);
        recyclerView=findViewById(R.id.steacher_recyid);
        this.setTitle("Employe Information");
        recyclerView.setHasFixedSize(true);
        progressBar=findViewById(R.id.stechprogg);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        uploadList=new ArrayList<>();
        firebaseStorage=FirebaseStorage.getInstance();
        String im=getIntent().getStringExtra("id");

    // databaseReference= FirebaseDatabase.getInstance().getReference("TecherDetailse");
      Query query=FirebaseDatabase.getInstance().getReference("TecherDetailse")
                .orderByChild("id").equalTo(im);

      query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    Uploadtest uploadtest=dataSnapshot1.getValue(Uploadtest.class);
                    uploadList.add(uploadtest);

                }
                teacherd_addapter=new Teacherdeta_addapter(Techer_alldetail_Show_Activity.this,uploadList);
                recyclerView.setAdapter(teacherd_addapter);

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


class Teacherdeta_addapter extends RecyclerView.Adapter<Teacherdeta_addapter.Techerd_ViewHolder>  {
    private Context context;
    private List<Uploadtest> uploadlist;


    public Teacherdeta_addapter(Context context, List<Uploadtest> uploadlist) {
        this.context = context;
        this.uploadlist = uploadlist;
    }

    @NonNull
    @Override
    public Techerd_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
       View view= layoutInflater.inflate(R.layout.teacher_all_item_layout,viewGroup,false);



        return new Techerd_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Techerd_ViewHolder techer_viewHolder, int i) {
        Uploadtest uploadtest=uploadlist.get(i);
        techer_viewHolder.name.setText("Name :"+uploadtest.getImageName());
        techer_viewHolder.fname.setText("Father Name :"+uploadtest.getFatherName());
        techer_viewHolder.mname.setText("Mother Name :"+uploadtest.getMotherName());
        techer_viewHolder.birthdate.setText("Date Of Birth :"+uploadtest.getDateofbirth());
        techer_viewHolder.religius.setText("Religius :"+uploadtest.getReligius());
        techer_viewHolder.gender.setText("Gender :"+uploadtest.getGender());
        techer_viewHolder.marrii.setText("Maritual Status :"+uploadtest.getRelation());
        techer_viewHolder.dep.setText("Department :"+uploadtest.getDepartmentName());
        techer_viewHolder.desgination.setText("desgination :"+uploadtest.getDesgination());
        techer_viewHolder.joindate.setText("Joining date :"+uploadtest.getJoindate());
        techer_viewHolder.edu.setText("Educational Qulaification :"+uploadtest.getEduquali());
        techer_viewHolder.mo.setText("Mobile No :"+uploadtest.getMobileNo());
        techer_viewHolder.email.setText("Emai :"+uploadtest.getEmail());
        techer_viewHolder.peradd.setText("Present Address :"+uploadtest.getPermanentAddress());
        techer_viewHolder.preseadd.setText("Permanent Address :"+uploadtest.getPrasentAddress());
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


    public class Techerd_ViewHolder extends RecyclerView.ViewHolder  {
        ImageView imageView;
        TextView name,dep,mo,email,edu,peradd,preseadd,
                religius,marrii,gender,fname,mname,joindate,birthdate,desgination;
        public Techerd_ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.steaimageid);
            name=itemView.findViewById(R.id.stecher_nameid);
            edu=itemView.findViewById(R.id.stecher_eduid);
            dep=itemView.findViewById(R.id.stecher_departmentid);
            peradd=itemView.findViewById(R.id.stecher_permanetaddid);
            preseadd=itemView.findViewById(R.id.stecher_prsentaddid);
            mo=itemView.findViewById(R.id.stecher_mobilenumberid);
            email=itemView.findViewById(R.id.stecher_emailid);
            religius=itemView.findViewById(R.id.stecher_relisiusid);
            marrii=itemView.findViewById(R.id.stecher_mriid);
            gender=itemView.findViewById(R.id.stecher_genderid);
            fname=itemView.findViewById(R.id.stecher_fnameid);
            mname=itemView.findViewById(R.id.stecher_mnameid);
            joindate=itemView.findViewById(R.id.stecher_joiningdateid);
            birthdate=itemView.findViewById(R.id.stecher_dateofbirthid);
            desgination=itemView.findViewById(R.id.stecher_desginationid);

        }
    }


   }
