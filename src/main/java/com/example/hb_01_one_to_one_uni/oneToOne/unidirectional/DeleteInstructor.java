package com.example.hb_01_one_to_one_uni.oneToOne.unidirectional;

import com.example.hb_01_one_to_one_uni.entity.Instructor;
import com.example.hb_01_one_to_one_uni.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteInstructor {
    public static void main(String[] args) {
        //  create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        //  create session
        Session session = factory.getCurrentSession();
        try {
            //start a transaction
            session.beginTransaction();

            //get instructor by primary key / id
            int theId=5;
            Instructor tempInstructor=session.get(Instructor.class,theId);

            System.out.println("Found instructor: "+tempInstructor);

            //delete the instructor
            if (tempInstructor!=null){
                System.out.println("Deleting Instructor: "+tempInstructor);

                //note: will also delete associated 'details' object
                //because of CascadeType.ALL
                //
                session.delete(tempInstructor);
            }
            //commit transaction
            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }
}
