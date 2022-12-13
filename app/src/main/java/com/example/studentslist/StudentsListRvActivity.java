package com.example.studentslist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.studentslist.model.Model;
import com.example.studentslist.model.Student;

import java.util.List;


public class StudentsListRvActivity extends AppCompatActivity {

	// Globals
	List<Student> data;
    RecyclerView listRv;
    StudentsAdapter adapter;
    Intent detailsIntent;
    Intent addIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_list_rv);
		
		        data= Model.instance.getAllStudents();

        listRv = findViewById(R.id.studentlist_rv);
        listRv.setHasFixedSize(true);
        listRv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new StudentsAdapter();
        listRv.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener()
         {
            @Override public void onItemClick(int position){
                detailsIntent = new
                        Intent(getApplicationContext(),
                        StudentDetailsActivity.class);
                detailsIntent.putExtra("pos",position);
                startActivity(detailsIntent);
            }
            @Override public void onCheckboxClick(int position, boolean isChecked){
                data.get(position).setFlag(isChecked);
            }

             @Override
             public void onAddBtnClick(int position) {
                 addIntent = new
                         Intent(getApplicationContext(),
                         AddStudentActivity.class);
                 startActivity(addIntent);
             }
         });
    }
	
	
    //HOLDER CLASS
    class StudentsViewHolder extends RecyclerView.ViewHolder{
        TextView nameTv;
        TextView idTv;
        CheckBox cb;
        ImageView avatar;
        Button addBtn;
        public StudentsViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
             nameTv = itemView.findViewById(R.id.listrow_name_tv);
             idTv = itemView.findViewById(R.id.listrow_id_tv);
             cb = itemView.findViewById(R.id.listrow_cb);
             avatar = itemView.findViewById(R.id.listrow_avatar_imv);
             addBtn = findViewById(R.id.studentlist_add_btn);
             itemView.setOnClickListener(v -> {
                 int pos = getAdapterPosition();
                 listener.onItemClick(pos);
             });
             cb.setOnClickListener(v -> {
                     int pos = getAdapterPosition();
                     listener.onCheckboxClick(pos, cb.isChecked());
             });
             addBtn.setOnClickListener(v->{
                 int pos = getAdapterPosition();
                 listener.onAddBtnClick(pos);
             });
        }
    }

    interface OnItemClickListener{
        void onItemClick(int position);
        void onCheckboxClick(int position, boolean isChecked);
        void onAddBtnClick(int position);
    }
    //ADAPTER CLASS
    class StudentsAdapter extends RecyclerView.Adapter<StudentsViewHolder>{

        OnItemClickListener listener;

         public void setOnItemClickListener(OnItemClickListener listener){
             this.listener = listener;
         }

        @NonNull
        @Override
        public StudentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.student_list_row,parent,false);
            StudentsViewHolder holder = new StudentsViewHolder(view, listener);

            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull StudentsViewHolder holder, int position) {

            Student student = data.get(position);
            holder.nameTv.setText(student.getName());
            holder.idTv.setText(student.getId());
            holder.cb.setChecked(student.isFlag());
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }
	
	    @Override
    protected void onResume() {
        super.onResume();
        data=Model.instance.getAllStudents();
        adapter.notifyDataSetChanged();
    }
}