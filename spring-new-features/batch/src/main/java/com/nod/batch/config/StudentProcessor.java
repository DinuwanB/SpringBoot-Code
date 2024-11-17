package com.nod.batch.config;

import com.nod.batch.student.Student;
import org.springframework.batch.item.ItemProcessor;

public class StudentProcessor implements ItemProcessor<Student, Student> {

    @Override
    public Student process(Student student) throws Exception {
        // business logic
        student.setId(null);
        return student;
    }

}
