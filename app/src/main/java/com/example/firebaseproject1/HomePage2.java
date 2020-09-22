package com.example.firebaseproject1;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

public class HomePage2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
CardView profile,students,employeS,admin,notices,website;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page2);
        drawerLayout=findViewById(R.id.navihome21id);
        NavigationView navigationView=findViewById(R.id.navigationhome2_id);
        navigationView.setNavigationItemSelectedListener(this);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.naviopen,R.string.naviclose);
        drawerLayout.addDrawerListener(toggle);
        this.setTitle("Home");
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        profile =findViewById(R.id.profileid);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Profile_Activity.class);
                startActivity(intent);
            }
        });
        students=findViewById(R.id.studentshowid);
        students.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Departmen_Name.class);
                intent.putExtra("contin","Student");
                startActivity(intent); }
        });
        employeS=findViewById(R.id.employeid);
        employeS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Departmen_Name.class);
                intent.putExtra("contin","Employee");
                startActivity(intent);
            }
        });
        admin=findViewById(R.id.adminid);
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
Intent intent=new Intent(getApplicationContext(),Loging_Page.class);

startActivity(intent);
            }
        });
        website=findViewById(R.id.websiteid);
        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Web_Site.class);
                startActivity(intent);
            }
        });
        notices=findViewById(R.id.noticeid);
        notices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Notice_Show.class);
                startActivity(intent);

            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        //Intent intent;
        if (menuItem.getItemId()==R.id.nevihomeid){
            Intent  intent =new Intent(HomePage2.this, HomePage2.class);
            startActivity(intent);
            finish();
        }
        else if (menuItem.getItemId()==R.id.neviexitid){
            finish();
        }else if (menuItem.getItemId()==R.id.nevicontactid){
            Intent  intent =new Intent(HomePage2.this, Contact_Number_Show.class);
            startActivity(intent);

        }else if (menuItem.getItemId()==R.id.nevifeadbackid){
            Intent intent=new Intent(getApplicationContext(),FeadBack_Page.class);
            startActivity(intent);
        }
        else{
            Intent  intent=new Intent(Intent.ACTION_SEND);
            final  String appPackageName=getApplicationContext().getPackageName();
            String appLink="";
            try {
                appLink="https:/play.google.com/store/apps/detailse?id="+appPackageName;

            }catch (android.content.ActivityNotFoundException anfe) {
                appLink = "https:/play.google.com/store/apps/detailse?id=" + appPackageName;
            }
            intent.setType("text/Link");
            String sharebody="Hey"
                    +"\n"+appLink;
            String sharesub="APP NAME";
            intent.putExtra(Intent.EXTRA_SUBJECT,sharesub);
            intent.putExtra(Intent.EXTRA_TEXT,sharebody);
            startActivity(Intent.createChooser(intent,"sharing")); }

        return false;
    }
    public void sh(){
        // Intent intent;

    }
}
