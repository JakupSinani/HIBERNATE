package com.example.hb_01_one_to_one_uni.oneToMany;

import com.example.hb_01_one_to_one_uni.entity.Instructor;
import com.example.hb_01_one_to_one_uni.entity.InstructorDetail;
import com.example.hb_01_one_to_one_uni.entity.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteCourse {
    public static void main(String[] args) {
        //  create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        //  create session
        Session session = factory.getCurrentSession();
        try {
            //  start a transaction
            session.beginTransaction();

            //  get a course
            int theId=2;
            Course tempCourse=session.get(Course.class,theId);

            //  delete course

            System.out.println("Deleting course: "+tempCourse);

            session.delete(tempCourse);

            //  commit transaction
            session.getTransaction().commit();

            System.out.println("DONE!");
        } finally {
            session.close();
            factory.close();
        }
    }
}
