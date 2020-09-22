package com.example.firebaseproject1;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

public class Technology_Layout extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technology__layout);
        drawerLayout=findViewById(R.id.ltechid);
        this.setTitle(R.string.ff1);
        NavigationView navigationView=findViewById(R.id.naviid);
        navigationView.setNavigationItemSelectedListener(this);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.naviopen,R.string.naviclose);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)){
            return true;
        } return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Intent intent;
        if (menuItem.getItemId()==R.id.suchona){
             intent =new Intent(getApplicationContext(), Shuchona_Page.class);
            startActivity(intent);
        }
        else if (menuItem.getItemId()==R.id.obo){
            intent=new Intent(getApplicationContext(),Obosthan_Layout.class);
            startActivity(intent);
        }
        else if (menuItem.getItemId()==R.id.itihas){
           intent=new Intent(getApplicationContext(),Itihas_Layout.class);
            startActivity(intent);

        }
        else if (menuItem.getItemId()==R.id.camp){
         intent=new Intent( getApplicationContext(),Campus_Layout.class);
            startActivity(intent); }
               else if (menuItem.getItemId()==R.id.suchona){
            intent=new Intent(Technology_Layout.this,Technology_Layout.class);
            startActivity(intent);
        } else if (menuItem.getItemId()==R.id.exit){
            finish();
        }
         return false;
    }
}
