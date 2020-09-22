package com.example.firebaseproject1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Student_Show_first_Lauout extends AppCompatActivity {
Spinner sseeion,deprtme;
    RadioGroup shift;
    TextView textView;
    RadioButton sh;
    ImageView imageView;
    ArrayAdapter <String> sesonAdapter,depAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__show_first__lauout);
        sseeion=findViewById(R.id.studentseeionidshow);
        this.setTitle("Student Information");
        imageView=findViewById(R.id.dimageid);
textView=findViewById(R.id.deid);

        shift=findViewById(R.id.stushiftidshow);

     /*  final String[] dep=getResources().getStringArray(R.array.departmentname);
        depAdapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.ssesion_layout,R.id.textviewids,dep);
        //deprtme.setAdapter(depAdapter);
*/
        final String[] ssenonao=getResources().getStringArray(R.array.sesion);
        sesonAdapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.ssesion_layout,R.id.textviewids,ssenonao);
        sseeion.setAdapter(sesonAdapter);
     String valu=getIntent().getStringExtra("depname");
        textView.setText(valu);

        if (valu.equals("Computer")){
            imageView.setImageResource(R.drawable.computericon);
        }else if (valu.equals("Civil")){
            imageView.setImageResource(R.drawable.civilicon);
        }
        else  if (valu.equals("Electrical")){
            imageView.setImageResource(R.drawable.electricalicon);
        }else if (valu.equals("Power")){
            imageView.setImageResource(R.drawable.powericon);
        }
        else  if (valu.equals("Mecanical")){
            imageView.setImageResource(R.drawable.mechnicalicon);
        }else if (valu.equals("AIDT")){
            imageView.setImageResource(R.drawable.arcicon);
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.showmnu_layout,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.showmenuid){
            Intent intent=new Intent(getApplicationContext(),Student_Show_first_Lauout.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    public void Goclick(View view) {
        int i= shift.getCheckedRadioButtonId();

        if (i<=0) {
            Toast.makeText(this, "Please Select Shift", Toast.LENGTH_SHORT).show();
            shift.requestFocus(); }
     /* else if (deprtme.getSelectedItemPosition()<=0){
            Toast.makeText(this, "Pease Select Department", Toast.LENGTH_SHORT).show();
            deprtme.requestFocus();
        } */else if (sseeion.getSelectedItemPosition()<=0){
            Toast.makeText(this, "Please Select Session", Toast.LENGTH_SHORT).show();
            sseeion.requestFocus();
        }else {
      String d=getIntent().getStringExtra("depnam");
            sh=findViewById(i);
            String s=sseeion.getSelectedItem().toString();
           //String d=textView.getText().toString();

            String sshh=sh.getText().toString();
            Intent intent=new Intent(getApplicationContext(),Students_Show.class);
            intent.putExtra("ses",s);
            intent.putExtra("dep",d);
            intent.putExtra("shi",sshh);
            startActivity(intent);
        }
    }
}
