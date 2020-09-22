package com.example.firebaseproject1;

import android.content.Intent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class Contact_Numbeer extends AppCompatActivity {
    Spinner spinner;
    ArrayAdapter<String> spinneradepter;
    StorageTask uploadtask;
    private FirebaseAuth mAuth;
    EditText editText,editText1;




    DatabaseReference databaseReference;
    StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_numbeer__add);
        spinner=findViewById(R.id.titelid);
        databaseReference= FirebaseDatabase.getInstance().getReference("Imergency_Number");
this.setTitle("Imergency Number Add");
        final String[] dep=getResources().getStringArray(R.array.titel);
        spinneradepter=new ArrayAdapter<String>(getApplicationContext(),R.layout.ssesion_layout,R.id.textviewids,dep);
       spinner.setAdapter(spinneradepter);
       editText=findViewById(R.id.conname);
       editText1=findViewById(R.id.connumber);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.edit_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.editicon){
            Intent intent=new Intent(getApplicationContext(),Contact_Number_Edit.class
            );
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }

    public void contacadd(View view) {
        if (editText.getText().toString().isEmpty()){
          editText.setError("Please Enter Name");  editText.requestFocus();
        }else if (editText1.getText().toString().isEmpty()){
            editText1.setError("Please Enter Mobile Number");
editText1.requestFocus();

        }
        else if (spinner.getSelectedItemId()==0){
            Toast.makeText(this, "Please Select Titel", Toast.LENGTH_SHORT).show();
        }
       else {

            if (uploadtask!=null && uploadtask.isInProgress()){
                Toast.makeText(this, "Uploading in Progress", Toast.LENGTH_SHORT).show();
            }else {
            String name=editText.getText().toString();
            String number=editText1.getText().toString();
            String title=spinner.getSelectedItem().toString();

            final String key=databaseReference.push().getKey();
            Uploadcontact ss=new Uploadcontact(key,name,number,title);
            databaseReference.child(key).setValue(ss);
            Toast.makeText(this, "Add Successfull", Toast.LENGTH_SHORT).show();
editText1.setText("");
editText.setText("");
spinner.setSelection(0);
editText.requestFocus();}
        }

    }




}
class Uploadcontact{
    String key,name,number,title;

    public Uploadcontact(Contact_Number_Edit contact_number_edit, List<Uploadcontact> uploadcontactList) {
    }

    public Uploadcontact() {
    }

    public Uploadcontact(String key, String name, String number, String title) {
        this.key = key;
        this.name = name;
        this.number = number;
        this.title = title;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
