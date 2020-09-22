package com.example.firebaseproject1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageTask;

import java.util.List;

public class Notice_Activity extends AppCompatActivity {
EditText textView;
    private FirebaseAuth mAuth;
    StorageTask uploadtask;
    DatabaseReference databaseReference;
    ListView listView;
    List<Uploadcontact> uploadcontactList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_);
        textView=findViewById(R.id.noticeaddid);
        this.setTitle("Notice Add");
        databaseReference= FirebaseDatabase.getInstance().getReference("Notice_All");

    }

    public void noticeaddclick(View view) {
        if (textView.getText().toString().isEmpty()){
           textView.setError("Please Enter Any Notice");
        }else {
            String notice=textView.getText().toString();
            if (uploadtask!=null && uploadtask.isInProgress()){
                Toast.makeText(this, "Uploading in Progress", Toast.LENGTH_SHORT).show();
            }else {
                final String key=databaseReference.push().getKey();
                UploadNotice ss=new UploadNotice(key,notice);
                databaseReference.child(key).setValue(ss);
                Toast.makeText(this, "Add Successfull", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
                textView.setText("");
            } }


    }
}
class UploadNotice{
    String key,notice;

  /*  public UploadNotice(Contact_Number_Edit contact_number_edit, List<Uploadcontact> uploadcontactList) {
    }
*/
    public UploadNotice() {
    }

    public UploadNotice(String key, String notice) {
        this.key = key;
        this.notice = notice;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }


  }
