package com.example.hb_01_one_to_one_uni.oneToMany.directional;

import com.example.hb_01_one_to_one_uni.entity.Instructor;
import com.example.hb_01_one_to_one_uni.entity.InstructorDetail;
import com.example.hb_01_one_to_one_uni.oneToMany.entity.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetInstructorCourses {
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

            //get the instructor from db
            int theId = 7;
            Instructor tempInstructor = session.get(Instructor.class, theId);
            System.out.println("Instructor: " + tempInstructor);

            //  get course for the instructor
            System.out.println("Courses: " + tempInstructor.getCourses());

            //  commit transaction
            session.getTransaction().commit();

            System.out.println("DONE!");
        } finally {
            session.close();
            factory.close();
        }
    }
}
