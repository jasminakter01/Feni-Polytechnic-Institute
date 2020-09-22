package com.example.firebaseproject1;

import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class  Teacher_Add_Layout extends AppCompatActivity {
    private FirebaseAuth mAuth;

    EditText name,designation,
            religius,father_Name,mother_name,
            mobile_no,email,present_address,permanent_address,
            educational_qualification;
    TextView date_of_birth,joining_date;
    ImageView imageView;
    RadioGroup department,maristatus,gender;
    RadioButton dep,gen,mari;
     Button add_click;

    Uri imageuri;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    DatePickerDialog datePickerDialog;
    StorageTask Uploadtask;
    DatePickerDialog.OnDateSetListener setListener1,setListener2;
    private static final int IMAGE_REQUEST=1;
    private static final int IMAGE_PICKE_CODE=1000;
    public static final int RESULT_OK  = -1;

    StorageTask uploadtask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher__add__layout);
        databaseReference= FirebaseDatabase.getInstance().getReference("TecherDetailse");
        storageReference= FirebaseStorage.getInstance().getReference("TecherDetailse");
        this.setTitle("Employe Information Add");
        mAuth = FirebaseAuth.getInstance();
        name=findViewById(R.id.nameid);
        designation=findViewById(R.id.designationid);
        religius=findViewById(R.id.religiusid);
                father_Name=findViewById(R.id.father_Nameid);
                mother_name=findViewById(R.id.mother_nameid);
                 mobile_no=findViewById(R.id.mobile_noid);
                email=findViewById(R.id.emailid);
                present_address=findViewById(R.id.present_addressid);
                permanent_address=findViewById(R.id.permanent_addressid);
               educational_qualification=findViewById(R.id.educational_qualificationid);

        maristatus=findViewById(R.id.maritialstatusid);
        department=findViewById(R.id.departmentid);
      gender=findViewById(R.id.genderid);



        date_of_birth=findViewById(R.id.date_of_birthid);
        joining_date=findViewById(R.id.joining_dateid);

        imageView=findViewById(R.id.picturid);
 add_click=findViewById(R.id.addclickid);

    }


 public void joiningdateclik(View view) {
        DatePicker datePicker=new DatePicker(this);
        int currentYear=datePicker.getYear();
        int currentMonth=datePicker.getMonth()+1;
        int currentDay=datePicker.getDayOfMonth();
        datePickerDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                joining_date.setText(dayOfMonth+"/"+(month+1)+"/"+year);
            }
        },currentYear,currentMonth,currentDay);
       datePickerDialog.show();
    }

    public void dateofbirthclik(View view) {
        DatePicker datePicker=new DatePicker(this);
        int currentYear=datePicker.getYear();
        int currentMonth=datePicker.getMonth()+1;
        int currentDay=datePicker.getDayOfMonth();
        datePickerDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                date_of_birth.setText(dayOfMonth+"/"+(month+1)+"/"+year);
            }
        },currentYear,currentMonth,currentDay);
        datePickerDialog.show();
    }

     public void pictureclik(View view) {
        openfilechoser();

    }

    public void addclick(View view) {

        int i= department.getCheckedRadioButtonId();
        int i2= maristatus.getCheckedRadioButtonId();
        int i3= gender.getCheckedRadioButtonId();






        if (name.getText().toString().isEmpty()) {
            name.setError("Enter The Name ");
            name.requestFocus();
        }

        else    if (i<=0) {
            Toast.makeText(this, "Department Is Empty", Toast.LENGTH_SHORT).show();
        }
        else if (designation.getText().toString().isEmpty()) {
            designation.setError("Enter Designation ");
            designation.requestFocus();
        }
        else if (date_of_birth.getText().toString().isEmpty()) {
            date_of_birth.setError("Enter Date Of Birth ");
            date_of_birth.requestFocus();
        } else    if (i3<=0) {
   Toast.makeText(this, "Gender Is Empty", Toast.LENGTH_SHORT).show();
        }
        else if (religius.getText().toString().isEmpty()){
            religius.setError("Enter Religious ");
            religius.requestFocus();
        }
        else    if (i2<=0) {
            Toast.makeText(this, "Relationship_Status Is Empty", Toast.LENGTH_SHORT).show();
        }
        else if (father_Name.getText().toString().isEmpty()) {
            father_Name.setError("Enter Father Name ");
            father_Name.requestFocus();
        } else if (mother_name.getText().toString().isEmpty()) {
            mother_name.setError("Enter Mother Name ");
            mother_name.requestFocus();
        }else if (mobile_no.getText().toString().isEmpty()) {
            mobile_no.setError("Enter Mobile Number ");
            mobile_no.requestFocus();
        }
        else if (email.getText().toString().isEmpty()){
            email.setError("Enter Email ");
            email.requestFocus();
        }
        else if (present_address.getText().toString().isEmpty()){
            present_address.setError("Enter Present Address");
            present_address.requestFocus();
        }else if (permanent_address.getText().toString().isEmpty()){
            permanent_address.setError("Enter Permanent Address");
            permanent_address.requestFocus();
        } else  if (joining_date.getText().toString().isEmpty()) {
            joining_date.setError("Enter Joining Date ");
            joining_date.requestFocus();
        }
        else  if (educational_qualification.getText().toString().isEmpty()) {
            educational_qualification.setError("Enter Educational Qualification ");
            educational_qualification.requestFocus();
        }else if(imageuri==null){
            Toast.makeText(this, "Please select Image", Toast.LENGTH_SHORT).show();
        }
        else{
        if (uploadtask!=null && uploadtask.isInProgress()){
            Toast.makeText(this, "Uploading in Progress", Toast.LENGTH_SHORT).show();
        }else {
 dep=findViewById(i);
            gen=findViewById(i3);
            mari=findViewById(i2);
        final String imagename=name.getText().toString();
            final String depvlue=dep.getText().toString();
        final String desgination=designation.getText().toString();
        final String dateofbirth=date_of_birth.getText().toString();
            final String genvalue=gen.getText().toString();
        final String relgius=religius.getText().toString();
            final String marivalue=mari.getText().toString();

        final String fatherName=father_Name.getText().toString();
        final String mothername=mother_name.getText().toString();
        final String mobile=mobile_no.getText().toString();
        final String emaile=email.getText().toString();

        final String prasentadd=present_address.getText().toString();
        final String permanenadd=permanent_address.getText().toString();
        final String joining=joining_date.getText().toString();
        final String edu=educational_qualification.getText().toString();

            FirebaseUser user=mAuth.getCurrentUser();
            final String id=databaseReference.push().getKey();
            StorageReference storageReference1=storageReference.child(System.currentTimeMillis()+"."+fileextention(imageuri));

        storageReference1.putFile(imageuri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        Toast.makeText(Teacher_Add_Layout.this, "Teacher Detailse Add Successfully", Toast.LENGTH_SHORT).show();
                        Task<Uri> UrlTask=taskSnapshot.getStorage().getDownloadUrl();
                        while (!UrlTask.isSuccessful());

                        Uri dwonloaduri=UrlTask.getResult();
                        Uploadtest upload=new Uploadtest(id,imagename,
                        depvlue,desgination,dateofbirth,
                        genvalue,relgius,marivalue,fatherName,
                        mothername,mobile,emaile
                        ,prasentadd,permanenadd,joining,edu,
                       dwonloaduri.toString());
              String uploadId=databaseReference.push().getKey();
              databaseReference.child(uploadId).setValue(upload);


                        name.setText("");
                      dep.setText("");
                       designation.setText("");
                       date_of_birth.setText("");
                    gen.setText("");
                       religius.setText("");
                      mari.setText("");

                        father_Name.setText("");
                        mother_name.setText("");
                        mobile_no.setText("");
                        email.setText("");

                       present_address.setText("");
                       permanent_address.setText("");
                        joining_date.setText("");
                        educational_qualification.setText("");
imageuri=null;
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                        Toast.makeText(Teacher_Add_Layout.this, "Teacher Detailse Add Not Successfully", Toast.LENGTH_SHORT).show();

                    }
                });}

      }


    }



    @Override

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==IMAGE_REQUEST && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            imageuri=data.getData();
            Picasso.with(this).load(imageuri).into(imageView);

        } }
    public String fileextention(Uri imageuri){
        ContentResolver contentResolver=getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(imageuri));

    }
     /*   public String getFileExtention(Uri imageuri){
    ContentResolver contentResolver=getContentResolver();
    MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(imageuri));
    }
*/
void openfilechoser() {

        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMAGE_REQUEST);
    }
}
class Uploadtest{
    String id;
    String imageName;
    String departmentName;
    String desgination;
    String dateofbirth;
    String gender;
    String religius;
    String relation;
    String fatherName;
    String motherName;
    String mobileNo;
    String email;
    String prasentAddress;
    String permanentAddress;
    String joindate;
    String eduquali;
    String imageUri;

    public Uploadtest() {
    }

    public Uploadtest(String id,String imageName, String departmentName, String desgination, String dateofbirth, String gender, String religius, String relation, String fatherName, String motherName, String mobileNo, String email, String prasentAddress, String permanentAddress, String joindate, String eduquali, String imageUri) {
       this.id=id;
        this.imageName = imageName;
        this.departmentName = departmentName;
        this.desgination = desgination;
        this.dateofbirth = dateofbirth;
        this.gender = gender;
        this.religius = religius;
        this.relation = relation;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.mobileNo = mobileNo;
        this.email = email;
        this.prasentAddress = prasentAddress;
        this.permanentAddress = permanentAddress;
        this.joindate = joindate;
        this.eduquali = eduquali;
        this.imageUri = imageUri;
    }
    @Exclude
    public String getid() {
        return id;
    }
    @Exclude
    public void setKey(String id) {
        this.id = id;
    }
    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDesgination() {
        return desgination;
    }

    public void setDesgination(String desgination) {
        this.desgination = desgination;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getReligius() {
        return religius;
    }

    public void setReligius(String religius) {
        this.religius = religius;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrasentAddress() {
        return prasentAddress;
    }

    public void setPrasentAddress(String prasentAddress) {
        this.prasentAddress = prasentAddress;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getJoindate() {
        return joindate;
    }

    public void setJoindate(String joindate) {
        this.joindate = joindate;
    }

    public String getEduquali() {
        return eduquali;
    }

    public void setEduquali(String eduquali) {
        this.eduquali = eduquali;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}

/*class studentindfo{
    String name,age;
    public studentindfo(){


    }
    public studentindfo(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}*/
