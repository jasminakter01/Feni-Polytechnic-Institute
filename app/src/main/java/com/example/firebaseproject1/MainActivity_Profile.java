package com.example.firebaseproject1;

import android.content.Intent;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity_Profile extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__profile);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
getMenuInflater().inflate(R.menu.menu_singout_layout,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       if (item.getItemId()==R.id.singoutid){
           FirebaseAuth.getInstance().signOut();
           finish();
           Intent intent=new Intent(getApplicationContext(),MainActivity.class);
           startActivity(intent);
       }




        return super.onOptionsItemSelected(item);



    }
}
