package com.maven.springMVC;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import java.util.List;

public class DatabaseHelper {

    private static SessionFactory factory;
    private static Session session;

    public DatabaseHelper(){
    }

    public int insertData(UserBean user){
        int result;
        session=getconnection();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
        factory.close();
        return 1;
    }

    public UserBean viewData(String username){
        session=getconnection();
        session.beginTransaction();
        Query query=session.createQuery("from UserBean u where u.username=:name");
        query.setParameter("name",username);
        List<UserBean> results=query.list();
        System.out.println(results);
        session.close();
        factory.close();
        if(!results.isEmpty()) {
            UserBean user = results.get(0);
            return user;
        }else{
            return null;
        }
    }

    public UserBean checkmail(String email){
        session=getconnection();
        session.beginTransaction();
        Query query=session.createQuery("from UserBean u where u.email=:email");
        query.setParameter("email",email);
        List<UserBean> results=query.list();
        System.out.println(results);
        session.close();
        factory.close();
        if(!results.isEmpty()) {
            UserBean user = results.get(0);
            return user;
        }else{
            return null;
        }
    }

    private Session getconnection(){
        factory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(UserBean.class).buildSessionFactory();
        return factory.getCurrentSession();
    }
}
