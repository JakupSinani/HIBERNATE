package com.example.hb_01_one_to_one_uni.oneToMany.unidirectional;

import com.example.hb_01_one_to_one_uni.entity.Course;
import com.example.hb_01_one_to_one_uni.entity.Instructor;
import com.example.hb_01_one_to_one_uni.entity.InstructorDetail;
import com.example.hb_01_one_to_one_uni.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetCourseAndReviews {
    public static void main(String[] args) {
        //  create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        //  create session
        Session session = factory.getCurrentSession();
        try {
            //  start a transaction
            session.beginTransaction();

            //  get the course
            int theId=3;
            Course tempCourse=session.get(Course.class,theId);
            //  print the course
            System.out.println(tempCourse);
            //  print the course review
            System.out.println(tempCourse.getReviews());
            //  commit transaction
            session.getTransaction().commit();

            System.out.println("DONE!");

        } finally {
            session.close();
            factory.close();
        }
    }
}
