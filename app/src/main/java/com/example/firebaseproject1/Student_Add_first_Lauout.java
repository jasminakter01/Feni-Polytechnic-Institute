package com.example.firebaseproject1;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Student_Add_first_Lauout extends AppCompatActivity {
Spinner sseeion, deprtme;
    RadioGroup shift;
    RadioButton sh;
    ArrayAdapter <String> sesonAdapter,depAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__add_first__lauout);
        sseeion=findViewById(R.id.studentseeionid);
        deprtme=findViewById(R.id.studepertmentid);
        shift=findViewById(R.id.stushiftid);
        this.setTitle("Student Information Add");

        final String[] dep=getResources().getStringArray(R.array.departmentname);
        depAdapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.ssesion_layout,R.id.textviewids,dep);
        deprtme.setAdapter(depAdapter);

        final String[] ssenonao=getResources().getStringArray(R.array.sesion);
        sesonAdapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.ssesion_layout,R.id.textviewids,ssenonao);
        sseeion.setAdapter(sesonAdapter);

    }

    public void okclik(View view) {
        int i= shift.getCheckedRadioButtonId();

        if (i<=0) {
            Toast.makeText(this, "Pease Select Shift", Toast.LENGTH_SHORT).show();
            shift.requestFocus(); }
        else if (deprtme.getSelectedItemPosition()<=0){
            Toast.makeText(this, "Pease Select Department", Toast.LENGTH_SHORT).show();
            deprtme.requestFocus();
        }  else if (sseeion.getSelectedItemPosition()<=0){
            Toast.makeText(this, "Pease Select Session", Toast.LENGTH_SHORT).show();
            sseeion.requestFocus();
        }else {
            sh=findViewById(i);
            String s=sseeion.getSelectedItem().toString();
            String d=deprtme.getSelectedItem().toString();

            String sshh=sh.getText().toString();
            Intent intent=new Intent(getApplicationContext(),Student_Add.class);
            intent.putExtra("ses",s);
            intent.putExtra("dep",d);
            intent.putExtra("shi",sshh);

            startActivity(intent);
        }

    }
}
