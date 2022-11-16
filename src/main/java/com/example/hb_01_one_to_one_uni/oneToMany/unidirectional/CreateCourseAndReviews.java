package com.example.hb_01_one_to_one_uni.oneToMany.unidirectional;

import com.example.hb_01_one_to_one_uni.entity.Course;
import com.example.hb_01_one_to_one_uni.entity.Instructor;
import com.example.hb_01_one_to_one_uni.entity.InstructorDetail;
import com.example.hb_01_one_to_one_uni.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndReviews {
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

            //  create a course
            Course tempCourse=new Course("Java - OOP");
            //  add some reviews
            tempCourse.addReview(new Review("Great course"));
            tempCourse.addReview(new Review("Cool course"));
            tempCourse.addReview(new Review("What a dumb course!!!"));
            //  save the course ... and leverage the cascade all
            System.out.println("Saving the course");
            System.out.println(tempCourse);
            System.out.println(tempCourse.getReviews());
            session.save(tempCourse);
            //  commit transaction
            session.getTransaction().commit();

            System.out.println("DONE!");
        } finally {
            session.close();
            factory.close();
        }
    }
}
