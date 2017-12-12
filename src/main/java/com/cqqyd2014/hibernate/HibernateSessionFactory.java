package com.cqqyd2014.hibernate;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


/**
 * Configures and provides access to Hibernate sessions, tied to the
 * current thread of execution.  Follows the Thread Local Session
 * pattern, see {@link http://hibernate.org/42.html }.
 */
public class HibernateSessionFactory {
	private static SessionFactory sessionFactory;
	 @SuppressWarnings("rawtypes")
	private static final ThreadLocal threadLocal = new ThreadLocal();  
    /**
     * 
     * @return Session
     */
    @SuppressWarnings("unchecked")
	public static Session getSession(){
    	Session session = (Session)threadLocal.get();  
        if(session == null){  
            if(sessionFactory == null){  
                try{  
                	final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                            .configure().build();
                    try
                    {
                        sessionFactory = new MetadataSources(registry).buildMetadata()
                                .buildSessionFactory();
                    } catch (Exception e)
                    {
                    	System.out.println(e.toString());
                        StandardServiceRegistryBuilder.destroy(registry);
                    }
                }catch(HibernateException e){  
                    System.out.println("Error Creating SessionFacotry.");  
                    e.printStackTrace();  
                }  
            }  
            session = sessionFactory.openSession();  
            threadLocal.set(session);  
        }  
        return session;  
    }
    
    @SuppressWarnings("unchecked")
	public static void closeSession(){
    	 Session session = (Session)threadLocal.get();  
         threadLocal.set(null);  
         if(session != null){  
             session.close();  
         }  
    }
}
