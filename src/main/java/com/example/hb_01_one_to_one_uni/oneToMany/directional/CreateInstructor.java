package com.example.hb_01_one_to_one_uni.oneToMany.directional;

import com.example.hb_01_one_to_one_uni.entity.Instructor;
import com.example.hb_01_one_to_one_uni.entity.InstructorDetail;
import com.example.hb_01_one_to_one_uni.oneToMany.entity.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateInstructor {
    public static void main(String[] args) {
        //  create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        //  create session
        Session session=factory.getCurrentSession();
        try {
            //  create the objects
            Instructor tempInstructor = new Instructor("Susan", "Simon", "susan.simon@gmail.com");

            InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.youtube.com/susan", "Guitar");

            //  associate the objects
            tempInstructor.setInstructorDetail(tempInstructorDetail);

            //  start a transaction
            session.beginTransaction();

            //  save the instructor
            System.out.println("Saving instructor: " + tempInstructor);
            session.save(tempInstructor);

            //  commit transaction
            session.getTransaction().commit();

            System.out.println("DONE!");
        } finally {
            session.close();
            factory.close();
        }
    }
}
