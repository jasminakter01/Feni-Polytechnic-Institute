package com.example.firebaseproject1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class Notice_Show extends AppCompatActivity {
    RecyclerView recyclerView;
    Notice_addapter  notice_addapter;
    List<UploadNotice> uploadList;
    DatabaseReference databaseReference;
    TextView textView;
    static  final int REQUEST_CALL=1;
    //String number="";
    ProgressBar progressBar;
    FirebaseStorage firebaseStorage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice__show);
        textView=findViewById(R.id.textviewnumberid);
        progressBar=findViewById(R.id.notice_progg);
        recyclerView=findViewById(R.id.notice_recyid);
        recyclerView.setHasFixedSize(true);
        this.setTitle("Notice");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        uploadList=new ArrayList<>();
        firebaseStorage=FirebaseStorage.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference("Notice_All");
        databaseReference.addValueEventListener(new ValueEventListener()  {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    UploadNotice uploadtest=dataSnapshot1.getValue(UploadNotice.class);
                    uploadList.add(uploadtest);
                }
                notice_addapter=new Notice_addapter(getApplicationContext(),uploadList);
                recyclerView.setAdapter(notice_addapter);
                progressBar.setVisibility(View.INVISIBLE);
                /*notice_addapter.setOnItemClickLisener(new Contact_Number_addapter.onItemClickLisener() {
                    @Override
                    public void onItemClick(int position) {
                        String notice = uploadList.get(position).getNotice();
                        // textView.setText(number);
                        Intent intent=new Intent(Intent.ACTION_CALL);
                        intent.setData(Uri.parse(notice));

                    } });*/
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Notice_Show.this, "Error "+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}

class Notice_addapter extends RecyclerView.Adapter<Notice_addapter.Notice_ViewHolder>  {
    private Context context;
    private List<UploadNotice> uploadlist;
    private onItemClickLisener lisener;


    public Notice_addapter(Context context, List<UploadNotice> uploadlist) {
        this.context = context;
        this.uploadlist = uploadlist;
    }




    @NonNull
    @Override
    public Notice_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view= layoutInflater.inflate(R.layout.noticeitem,viewGroup,false);



        return new Notice_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Notice_ViewHolder notice_viewHolder, int i) {
        UploadNotice uploadtest=uploadlist.get(i);
        notice_viewHolder.textView.setText(uploadtest.getNotice());

    }

    @Override
    public int getItemCount() {
        return uploadlist.size();
    }


    public class Notice_ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;


        public Notice_ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.notic_id);

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


