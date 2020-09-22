package com.example.firebaseproject1;

import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;

import android.os.Bundle;
import android.text.Editable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Student_Add extends AppCompatActivity {
    private static final int IMAGE_REQUEST=1;
    DatePickerDialog datePickerDialog;
    EditText Name,Roll,Reg,FatherName,MotherName,MobileNo,Email;
    Spinner blod;
    TextView dateofbirtht;
    StorageTask uploadtask;

    Uri imageuri;
    ImageView imageView;
    private static final int IMAGE_PICKE_CODE=1000;
    public static final int RESULT_OK  = -1;
    ArrayAdapter <String> sesonAdapter,depAdapter,bloodAddepter;
    private FirebaseAuth mAuth;



    DatabaseReference databaseReference;
    StorageReference storageReference;
    StorageTask Uploadtask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__add);
        String isesson=getIntent().getStringExtra("ses");
        String idepert=getIntent().getStringExtra("dep");
        String ishift=getIntent().getStringExtra("shi");
        this.setTitle("Student Information Add");

        String StudentData=isesson+"_"+idepert+"_"+ishift;
        databaseReference= FirebaseDatabase.getInstance().getReference(StudentData);
        storageReference= FirebaseStorage.getInstance().getReference(StudentData);
        mAuth = FirebaseAuth.getInstance();

        imageView=findViewById(R.id.studentpicturi);
        dateofbirtht=findViewById(R.id.student_dateofbirthid);

blod=findViewById(R.id.studentblodeid);
Name=findViewById(R.id.stunameid);
        Roll=findViewById(R.id.sturollid);
        Reg=findViewById(R.id.sturegid);
        FatherName=findViewById(R.id.stuFathernameid);
       MotherName=findViewById(R.id.stuMoternameid);
        MobileNo=findViewById(R.id.stumobileid);
        Email=findViewById(R.id.stuEmailid);


        final String[] blo=getResources().getStringArray(R.array.bloodname);
        bloodAddepter=new ArrayAdapter<String>(Student_Add.this,R.layout.ssesion_layout,R.id.textviewids,blo);
        blod.setAdapter(bloodAddepter);


    }


    public void pictureclik1(View view) {
        openfilechoser();

    }
    @Override

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==IMAGE_REQUEST && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            imageuri=data.getData();
            Picasso.with(this).load(imageuri).into(imageView);

        } }

    void openfilechoser() {

        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMAGE_REQUEST);
    }

    public void dateofbirth(View view) {
       DatePicker datePicker=new DatePicker(this);
        int currentYear=datePicker.getYear();
        int currentMonth=datePicker.getMonth()+1;
        int currentDay=datePicker.getDayOfMonth();
        datePickerDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dateofbirtht.setText(dayOfMonth+"/"+(month+1)+"/"+year);
            }
        },currentYear,currentMonth,currentDay);
        datePickerDialog.show();



    }
    void savedata(){

        if (Name.getText().toString().isEmpty()){
       Name.setError("Please Enter Name");
        Name.requestFocus();}
      else if (Roll.getText().toString().isEmpty()){
            Roll.setError("Please Enter Roll No");
            Roll.requestFocus();}
            else if (Reg.getText().toString().isEmpty()){
            Reg.requestFocus();
            Reg.setError("Please Enter Rafistation No");}
        else if (FatherName.getText().toString().isEmpty()){
            FatherName.requestFocus();
            FatherName.setError("Please Enter Father Name");}
        else if (MotherName.getText().toString().isEmpty()){
            MotherName.requestFocus();
            MotherName.setError("Please Enter Moher Name");}
        else if (MobileNo.getText().toString().isEmpty()){
            MobileNo.requestFocus();
            MobileNo.setError("Please Enter Mobile No");}
        else if (Email.getText().toString().isEmpty()){
            Email.requestFocus();
            Email.setError("Please Enter Email Address");}
        else if (dateofbirtht.getText().toString().isEmpty()){
            dateofbirtht.requestFocus();
            Toast.makeText(this, "Please Select Date Of Birth", Toast.LENGTH_SHORT).show();}

        else if (blod.getSelectedItemPosition()<=0){
            Toast.makeText(this, "Pease Select Blood", Toast.LENGTH_SHORT).show();
            blod.requestFocus();
        }else if(imageuri==null){
            Toast.makeText(this, "Please select Image", Toast.LENGTH_SHORT).show();
        }
       else {

            if (uploadtask!=null && uploadtask.isInProgress()){
                Toast.makeText(this, "Uploading in Progress", Toast.LENGTH_SHORT).show();
            }else
            {
                final String isesson=getIntent().getStringExtra("ses");
              final   String idepert=getIntent().getStringExtra("dep");
               final String ishift=getIntent().getStringExtra("shi");
                final String name=Name.getText().toString();
                final String roll=Roll.getText().toString();
                final String reg=Reg.getText().toString();
                final String fathername=FatherName.getText().toString();
                final String mothername=MotherName.getText().toString();
                final String mobile=MobileNo.getText().toString();
                final String email=Email.getText().toString();
                final String blood=blod.getSelectedItem().toString();
                final  String dateofbirth=dateofbirtht.getText().toString();
                final String department=idepert;
                final String shift=ishift;
                final String ssion=isesson;

                FirebaseUser user=mAuth.getCurrentUser();
                final String id=databaseReference.push().getKey();
                 StorageReference storageReference1=storageReference.child(System.currentTimeMillis()+"."+fileextention(imageuri));

storageReference1.putFile(imageuri)
        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
    @Override
    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
        Toast.makeText(getApplicationContext(), "Student Add Successfully", Toast.LENGTH_SHORT).show();
        Task<Uri> UrlTask=taskSnapshot.getStorage().getDownloadUrl();
        while (!UrlTask.isSuccessful());
        Uri dwonloaduri=UrlTask.getResult();
        Uploadstudent upload=new Uploadstudent(id,name,
                roll,reg
                ,fathername
                ,mothername,mobile,
                email,blood,
                department,
                shift,ssion, dwonloaduri.toString(),dateofbirth);
        String uploadId=databaseReference.push().getKey();
        databaseReference.child(uploadId).setValue(upload);

  Name.setText("");
     Roll.setText("");
      Reg.setText("");
        FatherName.setText("");
        MotherName.setText("");
        MobileNo.setText("");
        Email.setText("");
        blod.setSelection(0);
        dateofbirtht.setText("");
    imageView.setImageResource(R.drawable.ic_image_black_24dp);
        imageuri=null;

    }
}).addOnFailureListener(new OnFailureListener() {
    @Override
    public void onFailure(@NonNull Exception e) {
        Toast.makeText(getApplicationContext(), "Student Add Not Successfully", Toast.LENGTH_SHORT).show();

    }
});
            }}

    }

    public String fileextention(Uri imageuri){
        ContentResolver contentResolver=getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(imageuri));

    }

    public void studentaddclick(View view) {
        savedata();
    }
}
class Uploadstudent{
    String id,name,roll,reg,fathername,mothername,mobile,email,blood,department,shift,ssion,imageuri,dateofbirth;

    public Uploadstudent() {
    }

    public Uploadstudent(String id, String name, String roll, String reg, String fathername, String mothername, String mobile, String email, String blood, String department, String shift, String ssion, String imageuri, String dateofbirth) {
        this.id = id;
        this.name = name;
        this.roll = roll;
        this.reg = reg;
        this.fathername = fathername;
        this.mothername = mothername;
        this.mobile = mobile;
        this.email = email;
        this.blood = blood;
        this.department = department;
        this.shift = shift;
        this.ssion = ssion;
        this.imageuri = imageuri;
        this.dateofbirth = dateofbirth;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getReg() {
        return reg;
    }

    public void setReg(String reg) {
        this.reg = reg;
    }

    public String getFathername() {
        return fathername;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
    }

    public String getMothername() {
        return mothername;
    }

    public void setMothername(String mothername) {
        this.mothername = mothername;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getSsion() {
        return ssion;
    }

    public void setSsion(String ssion) {
        this.ssion = ssion;
    }

    public String getImageuri() {
        return imageuri;
    }

    public void setImageuri(String imageuri) {
        this.imageuri = imageuri;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }
}