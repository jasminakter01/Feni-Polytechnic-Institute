package com.example.firebaseproject1;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
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

import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Contact_Number_Show extends AppCompatActivity {
    RecyclerView recyclerView;
    Contact_Number_addapter contact_number_addapter;
    List<Uploadcontact> uploadList;
    DatabaseReference databaseReference;
    TextView textView;
    static  final int REQUEST_CALL=1;
  //String number="";
    ProgressBar progressBar;
    FirebaseStorage firebaseStorage;
    Uploadcontact uploadcontact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact__number__show);
        recyclerView=findViewById(R.id.contact_number_recyid);
        this.setTitle("Imergency Number");
        recyclerView.setHasFixedSize(true);
        progressBar=findViewById(R.id.contact_number_progg);
        textView=findViewById(R.id.textviewnumber);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        uploadList=new ArrayList<>();
        firebaseStorage=FirebaseStorage.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference("Imergency_Number");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    Uploadcontact uploadtest=dataSnapshot1.getValue(Uploadcontact.class);
                    uploadList.add(uploadtest);
                }
                contact_number_addapter=new Contact_Number_addapter(getApplicationContext(),uploadList);
                recyclerView.setAdapter(contact_number_addapter);
                progressBar.setVisibility(View.INVISIBLE);
                contact_number_addapter.setOnItemClickLisener(new Contact_Number_addapter.onItemClickLisener() {
                    @Override
                    public void onItemClick(int position) {
                        String number = uploadList.get(position).getNumber();
                   // textView.setText(number);
         Intent intent=new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:"+number));
         if (ActivityCompat.checkSelfPermission(Contact_Number_Show.this,Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED){
   return;
        }
            startActivity(intent);
  } });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Contact_Number_Show.this, "Error "+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

class Contact_Number_addapter extends RecyclerView.Adapter<Contact_Number_addapter.Contact_Number_ViewHolder>  {
    private Context context;
    private List<Uploadcontact> uploadlist;
    private onItemClickLisener lisener;


    public Contact_Number_addapter(Context context, List<Uploadcontact> uploadlist) {
        this.context = context;
        this.uploadlist = uploadlist;
    }




    @NonNull
    @Override
    public Contact_Number_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view= layoutInflater.inflate(R.layout.contact_item_layout,viewGroup,false);



        return new Contact_Number_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Contact_Number_ViewHolder contact_number_viewHolder, int i) {
        Uploadcontact uploadtest=uploadlist.get(i);
        contact_number_viewHolder.textView.setText("Title :"+uploadtest.getTitle());
        contact_number_viewHolder.textView2.setText("Name :"+uploadtest.getName());
        contact_number_viewHolder.textView3.setText("Number :"+uploadtest.getNumber());


    }

    @Override
    public int getItemCount() {
        return uploadlist.size();
    }


    public class Contact_Number_ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView,textView2,textView3;


        public Contact_Number_ViewHolder(@NonNull View itemView) {
            super(itemView);
             textView=itemView.findViewById(R.id.con_num_titel);
            textView2=itemView.findViewById(R.id.con_num_name);
            textView3=itemView.findViewById(R.id.con_num_number);
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




