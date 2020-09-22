package com.example.firebaseproject1;

import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

public class Shuchona_Page extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suchona_page);
        this.setTitle(R.string.ff1);

        drawerLayout=findViewById(R.id.dr);

        NavigationView navigationView=findViewById(R.id.naviid);
        navigationView.setNavigationItemSelectedListener(this);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.naviopen,R.string.naviclose);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_layout,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)){
            return true;

        }else if (item.getItemId()==R.id.Addmissionid){
        Intent intent=new Intent(getApplicationContext(),Addmission_Layout.class);
        startActivity(intent);
         }return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Intent intent;
       if (menuItem.getItemId()==R.id.obo){
            intent=new Intent(Shuchona_Page.this,Obosthan_Layout.class);
            startActivity(intent);
        }
        else if (menuItem.getItemId()==R.id.itihas){
         intent=new Intent(Shuchona_Page.this,Itihas_Layout.class);
            startActivity(intent);

        }
        else if (menuItem.getItemId()==R.id.camp){
           intent=new Intent(Shuchona_Page.this,Campus_Layout.class);
            startActivity(intent); }
        else if (menuItem.getItemId()==R.id.tech){
           intent=new Intent(Shuchona_Page.this,Technology_Layout.class);
            startActivity(intent);}
            else if (menuItem.getItemId()==R.id.suchona){
           intent=new Intent(Shuchona_Page.this, Shuchona_Page.class);
           startActivity(intent);
       } else if (menuItem.getItemId()==R.id.exit){
           finish();
       }

        return false;
    }
}
