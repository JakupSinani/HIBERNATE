package com.example.hb_01_one_to_one_uni.oneToOne.unidirectional;
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
            //  create the objects

            Instructor tempInstructor = new Instructor("Jakup","Sinani","Jakup.sinanii1@gmail.com");
            InstructorDetail tempInstructorDetail=new InstructorDetail("http://www.youtube.com/JakupSinani","Coding");

            /*Instructor tempInstructor = new Instructor("Sinan","Sinani","Sinan.sinanii1@gmail.com");
            InstructorDetail tempInstructorDetail=new InstructorDetail("http://www.youtube.com/sssJakupSinani","Tennis");*/

            //  associate the object

            tempInstructor.setInstructorDetail(tempInstructorDetail);

            //  start a transaction

            session.beginTransaction();

            // save the instructor
            //
            //Note: this will also save the details object
            //because of CascadeTupe.ALL
            //
            System.out.println("Saving instructor: "+tempInstructor);
            session.save(tempInstructor);

            //  save the instructor

            //commit transaction
            session.getTransaction().commit();

            System.out.println("DONE!");
        } finally {
            factory.close();
        }
    }

}
