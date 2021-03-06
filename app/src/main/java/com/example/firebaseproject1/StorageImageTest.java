package com.example.firebaseproject1;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;

import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.google.firebase.storage.internal.Util;
import com.squareup.picasso.Downloader;
import com.squareup.picasso.Picasso;

import java.net.URI;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class StorageImageTest extends AppCompatActivity implements View.OnClickListener {
            EditText editText;
            Button button1,button2,button3;
            ImageView imageView;
            ProgressBar progressBar;
            Uri imageuri;
            DatabaseReference databaseReference;
            StorageReference storageReference;
            StorageTask uploadtask;
            private static final int IMAGE_REQUEST=1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage_image_test);
        editText=findViewById(R.id.imagenameid2);
        button1=findViewById(R.id.saveimageid2);
        imageView=findViewById(R.id.imageid2);
        button2=findViewById(R.id.displayimageid2);
        button3=findViewById(R.id.chooseimageid2);
        progressBar=findViewById(R.id.progressbarid2);
           storageReference= FirebaseStorage.getInstance().getReference("storageimage");

        databaseReference= FirebaseDatabase.getInstance().getReference("storageimage");


        button3.setOnClickListener(this);
        button2.setOnClickListener(this);
        button1.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
    switch (v.getId()){
        case R.id.chooseimageid2:
        openfilechoser();
            break;
        case R.id.displayimageid2:
            Intent intent=new Intent(StorageImageTest.this,Show_Image.class);
            startActivity(intent);


            break;
        case R.id.saveimageid2:
if (uploadtask!=null && uploadtask.isInProgress()){
    Toast.makeText(this, "Uploading in Progress", Toast.LENGTH_SHORT).show();
}else {
    savedata();
} break;
    }
    }

    private void savedata() {
final String imagename=editText.getText().toString().trim();
if (imagename.isEmpty()){

    editText.setError("Enter The Image Name ");
    editText.requestFocus();
    return;
        }else{
    final String id=databaseReference.push().getKey();

    StorageReference storageReference1=storageReference.child(System.currentTimeMillis()+"."+fileextention(imageuri));
    storageReference1.putFile(imageuri)
            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    // Get a URL to the uploaded content
                    editText.setText("");
                    Toast.makeText(StorageImageTest.this, "image is stored successfull ", Toast.LENGTH_SHORT).show();
                    Task<Uri>UrlTask=taskSnapshot.getStorage().getDownloadUrl();
                    while (!UrlTask.isSuccessful());
                    Uri dwonloaduri=UrlTask.getResult();
                    Upload upload=new Upload(imagename,id,dwonloaduri.toString());
                    String uploadid=databaseReference.push().getKey();
                    databaseReference.child(uploadid).setValue(upload);
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle unsuccessful uploads
                    // ...
                    Toast.makeText(StorageImageTest.this, "image is not stored successfull", Toast.LENGTH_SHORT).show();
                }
            });}


    }

    void openfilechoser() {

      Intent intent=new Intent();
      intent.setType("image/*");
      intent.setAction(intent.ACTION_GET_CONTENT);
      startActivityForResult(intent,IMAGE_REQUEST);
}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
    if (requestCode==IMAGE_REQUEST && resultCode==RESULT_OK && data!=null && data.getData()!=null){
        imageuri=data.getData();
        Picasso.with(this).load(imageuri).into(imageView);
 }
    }
    public String fileextention(Uri imageuri){
        ContentResolver contentResolver=getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(imageuri));

    }
}






 class Upload {
    String imagename,imageurl;
String key;
@Exclude
     public String getKey() {
         return key;
     }
     @Exclude
     public void setKey(String key) {
         this.key = key;
     }

     public Upload() {

     }

     public Upload(String imagename,String key, String imageurl) {
        this.imagename = imagename;
         this.key = key;
        this.imageurl = imageurl;

    }

    public String getImagename() {

       return imagename; }

    public void setImagename(String imagename) {
        this.imagename = imagename;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
