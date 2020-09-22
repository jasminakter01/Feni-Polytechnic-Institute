package com.example.firebaseproject1;

import android.content.Intent;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    EditText editText1,editText2;
    Button button;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Sing in");
        mAuth = FirebaseAuth.getInstance();
        editText1=findViewById(R.id.edt1);
        editText2=findViewById(R.id.edt2);
        button=findViewById(R.id.ms);
    }

    public void login(View view) {
        String email=editText1.getText().toString().trim();
        String password=editText2.getText().toString().trim();
        if (email.isEmpty()){
            editText1.setError("Enter Email Address ");
            editText1.requestFocus();
            return;

        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editText1.setError("Enter Valied Email Address ");
            editText1.requestFocus();
            return;

        }
        if (password.isEmpty()){
            editText2.setError("Enter Password ");
            editText2.requestFocus();
            return;
        }
        if (password.length()<=6){
            editText2.setError("Minimum Length of a password should be 6");
            editText2.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
if(task.isSuccessful()){
    finish();
    Intent intent=new Intent(MainActivity.this,MainActivity_Profile.class);
   intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    startActivity(intent);

}else{
    Toast.makeText(MainActivity.this, "Login UnSuccess Full", Toast.LENGTH_SHORT).show();
}
            }
        });

    }

    public void textviewclick(View view) {
        Intent intent=new Intent(MainActivity.this,Sing_In_Activity.class);
        startActivity(intent);
    }
}
