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
public class Students_Show extends AppCompatActivity {
    RecyclerView recyclerView;
    Stdent_addapter student_addapter;
    List<Uploadstudent> uploadList;
    DatabaseReference databaseReference;
    ProgressBar progressBar;
    FirebaseStorage firebaseStorage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students__show);
        recyclerView=findViewById(R.id.sutdent_recyid);
        recyclerView.setHasFixedSize(true);
        this.setTitle("Student Information");
        progressBar=findViewById(R.id.stuprogg);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        uploadList=new ArrayList<>();
        firebaseStorage=FirebaseStorage.getInstance();
        final String isesson=getIntent().getStringExtra("ses");
        final   String idepert=getIntent().getStringExtra("dep");
        final String ishift=getIntent().getStringExtra("shi");
        String valu=isesson+"_"+idepert+"_"+ishift;
        databaseReference=FirebaseDatabase.getInstance().getReference(valu);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    Uploadstudent uploadtest=dataSnapshot1.getValue(Uploadstudent.class);
                    uploadList.add(uploadtest);
                    Toast.makeText(Students_Show.this, ""+databaseReference, Toast.LENGTH_SHORT).show();

                }
                student_addapter=new Stdent_addapter(getApplicationContext(),uploadList);
                recyclerView.setAdapter(student_addapter);
                student_addapter.setOnItemClickLisener(new Stdent_addapter.onItemClickLisener() {
                    @Override
                    public void onItemClick(int position) {

                        String id=uploadList.get(position).getId();
                        String isesson=getIntent().getStringExtra("ses");
                        String idepert=getIntent().getStringExtra("dep");
                        String ishift=getIntent().getStringExtra("shi");
                        String valu=isesson+"_"+idepert+"_"+ishift;

                        Intent intent=new Intent(getApplicationContext(),Student_Show_All_Detailse.class);
                        intent.putExtra("datname",valu);

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
class Stdent_addapter extends RecyclerView.Adapter<Stdent_addapter.Student_ViewHolder>  {
    private Context context;
    private List<Uploadstudent> uploadlist;
    private onItemClickLisener lisener;


    public Stdent_addapter(Context context, List<Uploadstudent> uploadlist) {
        this.context = context;
        this.uploadlist = uploadlist;
    }




    @NonNull
    @Override
    public Student_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view= layoutInflater.inflate(R.layout.student_item_layout,viewGroup,false);



        return new Student_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Student_ViewHolder student_viewHolder, int i) {
        Uploadstudent uploadtest=uploadlist.get(i);
        student_viewHolder.textView.setText("Name :"+uploadtest.getName());
        student_viewHolder.textView2.setText("Roll :"+uploadtest.getRoll());
        student_viewHolder.textView3.setText("Reg :"+uploadtest.getReg());
        Picasso.with(context)
                .load(uploadtest.imageuri).
                placeholder(R.mipmap.ic_launcher_round)
                .fit()
                .centerCrop()
                .into(student_viewHolder
                        .imageView);


    }

    @Override
    public int getItemCount() {
        return uploadlist.size();
    }


    public class Student_ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView,textView2,textView3;
        ImageView imageView;

        public Student_ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.stu_image);
            textView=itemView.findViewById(R.id.stu_name);
            textView2=itemView.findViewById(R.id.stu_roll);
            textView3=itemView.findViewById(R.id.stu_reg);
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
