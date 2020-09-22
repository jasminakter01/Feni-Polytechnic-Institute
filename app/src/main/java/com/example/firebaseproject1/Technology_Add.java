package com.example.firebaseproject1;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

import androidx.appcompat.app.AppCompatActivity;

public class Technology_Add extends AppCompatActivity {
    EditText editText;
    ArrayAdapter<String> spinneradepter;
    StorageTask uploadtask;
    private FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technology__add);
        editText=findViewById(R.id.techname);
        databaseReference= FirebaseDatabase.getInstance().getReference("Technology");
this.setTitle("New Department Add");
    }

    public void techadd(View view) {
        if (editText.getText().toString().isEmpty()){
            editText.setError("Please Technology Name");
            editText.requestFocus();
        }
        else {

            if (uploadtask!=null && uploadtask.isInProgress()){
                Toast.makeText(this, "Uploading in Progress", Toast.LENGTH_SHORT).show();
            }else {
                String techNAme=editText.getText().toString();
                final String key=databaseReference.push().getKey();
                UploadTechnology ss=new UploadTechnology(key,techNAme);
                databaseReference.child(key).setValue(ss);
                Toast.makeText(this, "Add Successfull", Toast.LENGTH_SHORT).show();
                editText.setText("");
            }







        }

    }
}
    class UploadTechnology{
    String techName,key;

        public UploadTechnology() {
        }

        public UploadTechnology(String techName, String key) {
            this.techName = techName;
            this.key = key;
        }

        public String getTechName() {
            return techName;
        }

        public void setTechName(String techName) {
            this.techName = techName;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }


