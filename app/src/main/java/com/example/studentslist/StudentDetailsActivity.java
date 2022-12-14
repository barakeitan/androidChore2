package com.example.studentslist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.studentslist.model.Model;
import com.example.studentslist.model.Student;

public class StudentDetailsActivity extends AppCompatActivity {

    Student student;
    TextView nameTv;
    TextView idTv;
    TextView phoneTv;
    TextView addressTv;
    CheckBox cb;
    Button editBtn;
    Button backBtn;
    ImageView avatar;
    Intent EditIntent;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);
		
        nameTv = findViewById(R.id.details_name_txt);
        idTv = findViewById(R.id.details_id_txt);
        phoneTv = findViewById(R.id.details_phone_txt);
        addressTv = findViewById(R.id.details_address_txt);
        cb = findViewById(R.id.details_checked_chk);
        avatar = findViewById(R.id.details_student_imgv);
        editBtn=findViewById(R.id.details_to_edit_btn);
        backBtn=findViewById(R.id.details_back_btn);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            pos = extras.getInt("pos");
            student = Model.instance.getAllStudents().get(pos);
            nameTv.setText(student.getName());
            idTv.setText(student.getId());
            addressTv.setText(student.getAddress());
            phoneTv.setText(student.getPhone());
            cb.setChecked(student.isFlag());
        }
        editBtn.setOnClickListener(v -> {
            EditIntent = new Intent(v.getContext(),
                    EditStudentActivity.class);
            EditIntent.putExtra("pos",pos);
            startActivity(EditIntent);
        });
        backBtn.setOnClickListener(v -> {
            finish();
        });
    }
	
    @Override
    protected void onStart() {
        super.onStart();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            pos = extras.getInt("pos");
            student = Model.instance.getAllStudents().get(pos);
            nameTv.setText(student.getName());
            idTv.setText(student.getId());
            addressTv.setText(student.getAddress());
            phoneTv.setText(student.getPhone());
            cb.setChecked(student.isFlag());
        }
    }
}