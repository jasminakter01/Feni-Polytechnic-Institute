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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Sing_In_Activity extends AppCompatActivity {
    TextView textView;
    EditText editText1,editText2;
    Button button;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing__in_);
        this.setTitle("Sing up");

        editText1=findViewById(R.id.edt3);
        editText2=findViewById(R.id.edt4);
        mAuth = FirebaseAuth.getInstance();
        button=findViewById(R.id.bs);
    }

    public void login2(View view) {
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
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
if (task.isSuccessful()){
    finish();
    Intent intent=new Intent(Sing_In_Activity.this,MainActivity_Profile.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    startActivity(intent);

}else {
    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
        Toast.makeText(Sing_In_Activity.this, "User Is Allrady Ragistered ", Toast.LENGTH_SHORT).show();
    }else{
        Toast.makeText(Sing_In_Activity.this, "Error "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
    }
}
            }
        });
    }

    public void textviewclick2(View view) {
finish();
        Intent intent=new Intent(Sing_In_Activity.this,MainActivity.class);
        startActivity(intent);
    }
}
