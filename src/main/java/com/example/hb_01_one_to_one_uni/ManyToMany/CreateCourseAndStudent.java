package com.example.hb_01_one_to_one_uni.ManyToMany;

import com.example.hb_01_one_to_one_uni.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndStudent {
    public static void main(String[] args) {
        //  create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        //  create session
        Session session = factory.getCurrentSession();
        try {
            //  start a transaction
            session.beginTransaction();

            //  create a course
            Course tempCourse=new Course("Java - OOP");

            //  save the course
            System.out.println("\nSaving the course ...");
            session.save(tempCourse);
            System.out.println("Saved the course: "+tempCourse);

            //  create the students
            Student tempStudent1=new Student("Jakup","Sinani","jakup@gmail.com");
            Student tempStudent2=new Student("Emir","Aliu","emir@gmail.com");
            //  add students to the course
            tempCourse.addStudent(tempStudent1);
            tempCourse.addStudent(tempStudent2);
            //  save the students
            System.out.println("\nSaving students....");
            session.save(tempStudent1);
            session.save(tempStudent2);
            System.out.println("Saved students: "+tempCourse.getStudents());
            //commit transaction
            session.getTransaction().commit();

            System.out.println("DONE!");

        } finally {
            session.close();
            factory.close();
        }
    }
}
