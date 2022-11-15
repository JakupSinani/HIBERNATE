package com.example.hb_01_one_to_one_uni.oneToOne.bidirectional;

import com.example.hb_01_one_to_one_uni.entity.Instructor;
import com.example.hb_01_one_to_one_uni.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateInstructor {
    public static void main(String[] args) {
        //  create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        //  create session
        Session session=factory.getCurrentSession();

        try {
            //start a transaction
            session.beginTransaction();

            //get the instructor detail objecct
            int theId=4;
            InstructorDetail tempInstructorDetail=session.get(InstructorDetail.class,theId);

            //print the instructor detail
            System.out.println("tempInstructorDetail: "+tempInstructorDetail);

            //print the associated instructor
            System.out.println("The associated instructor: "+tempInstructorDetail.getInstructor());

            //commit transaction
            session.getTransaction().commit();

            System.out.println("DONE!");
        } finally {
            factory.close();
        }
    }

}
