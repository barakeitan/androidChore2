package com.example.class2demo2.model;

import com.example.class2demo2.R;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Model {
    public static final Model instance = new Model();
    List<Student> data = new LinkedList<Student>();

    private Model(){
        Random random = new Random();
        int id = 0;
        for(int i=0;i<20;i++){
            id = 100000000 + random.nextInt(1000000000);
            Student s = new Student("john doe no. "+i,""+ id, "050-123-456"+i,"The closet under the stairs "+i, false, R.drawable.profile);
            data.add(s);
        }
    }

//    List<Student> data = new LinkedList<Student>();

    public List<Student> getAllStudents() {
        return data;
    }
    public void addStudent(Student student){
        data.add(student);
    }
}

