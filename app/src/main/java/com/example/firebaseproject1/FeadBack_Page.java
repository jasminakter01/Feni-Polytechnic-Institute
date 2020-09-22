package com.example.firebaseproject1;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class FeadBack_Page extends AppCompatActivity {
EditText editText,editText1;
Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fead_back__page);
        this.setTitle("Apps Feadback");
        editText1=findViewById(R.id.edittext2id);
        button=findViewById(R.id.buuid);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText1.getText().toString().isEmpty()){
                editText1.setError("Please Enter Comments");
                editText1.requestFocus();
            }
                else {
                String e1,e2;
                e2=  editText1.getText().toString();
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("text/email");
                intent.putExtra(Intent.EXTRA_EMAIL,new String[]{"jasmin.akter283997@gmail.com"});

                intent.putExtra(Intent.EXTRA_SUBJECT,"Apps Fead Back");
                intent.putExtra(Intent.EXTRA_TEXT,"Massage :"+e2);
                startActivity(Intent.createChooser(intent,"Feadback with"));

                editText1.setText("");
            }
        }
        });
    }
}
