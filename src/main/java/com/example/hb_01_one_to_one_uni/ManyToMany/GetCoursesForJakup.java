package com.example.hb_01_one_to_one_uni.ManyToMany;

import com.example.hb_01_one_to_one_uni.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetCoursesForJakup {
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

            //  get the student jakup from database
            int studentId=1;
            Student tempStudent=session.get(Student.class,studentId);
            System.out.println("\nLoaded student: "+tempStudent);
            System.out.println("Courses: "+tempStudent.getCourses());
            //  commit transaction
            session.getTransaction().commit();

            System.out.println("DONE!");
        } finally {
            session.close();
            factory.close();
        }
    }
}
