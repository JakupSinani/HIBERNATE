package com.example.hb_01_one_to_one_uni.oneToMany.directional;

import com.example.hb_01_one_to_one_uni.entity.Instructor;
import com.example.hb_01_one_to_one_uni.entity.InstructorDetail;
import com.example.hb_01_one_to_one_uni.entity.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourse {
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


            //  start a transaction
            session.beginTransaction();

           // get the instructor from db
            int theId=7;
            Instructor tempInstructor=session.get(Instructor.class,theId);

            //  create some course
            Course tempCourse1=new Course("Air Guitar - The Ultimate Guide");
            Course tempCourse2=new Course("The Pinball Masterclass");

            //  add courses to instructor
            tempInstructor.add(tempCourse1);
            tempInstructor.add(tempCourse2);

            //  save the courses
            session.save(tempCourse1);
            session.save(tempCourse2);
            //  commit transaction
            session.getTransaction().commit();

            System.out.println("DONE!");

        } finally {
            session.close();
            factory.close();
        }
    }
}
