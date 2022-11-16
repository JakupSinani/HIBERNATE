package com.example.hb_01_one_to_one_uni.fetchtype;

import com.example.hb_01_one_to_one_uni.entity.Course;
import com.example.hb_01_one_to_one_uni.entity.Instructor;
import com.example.hb_01_one_to_one_uni.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EagerLazyType {
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

            //  get the instructor from db
            int theId = 7;
            Instructor tempInstructor = session.get(Instructor.class, theId);

            System.out.println("Instructor: " + tempInstructor);

            //  get courses for the instructor
            System.out.println("Courses: " + tempInstructor.getCourses());

            //  commit transaction
            session.getTransaction().commit();

            //  since courses are lazy loaded ... this should fail
            //  close the session
            session.close();

            System.out.println("\nThe session is now closed!\n");
            // option 1: call getter method while session is open
            //  get courses for the instructor

            System.out.println("Courses: " + tempInstructor.getCourses());


            System.out.println("DONE!");
        } finally {
            session.close();
            factory.close();
        }
    }
}
