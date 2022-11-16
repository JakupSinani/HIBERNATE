package com.example.hb_01_one_to_one_uni.fetchtype;

import com.example.hb_01_one_to_one_uni.entity.Course;
import com.example.hb_01_one_to_one_uni.entity.Instructor;
import com.example.hb_01_one_to_one_uni.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class FetchJoin {
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
            // start a transaction
            session.beginTransaction();

            //  option 2: Hibernate query with HQL

            //  get the instructor from db
            int theId=7;
            Query<Instructor> query=session.createQuery("select i from Instructor i "
                    +"JOIN FETCH i.courses"
                    +"where i.id = :theInstructorID",
                    Instructor.class);

            //  set parameter on query
            query.setParameter("TheInstructorId",theId);

            //  execute query and get instructor
            Instructor tempInstructor=query.getSingleResult();

            System.out.println("Instructor: "+tempInstructor);
        } finally {
            session.close();
            factory.close();
        }
    }
}
