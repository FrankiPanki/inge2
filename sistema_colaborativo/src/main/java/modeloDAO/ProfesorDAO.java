/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloDAO;

import java.util.List;
import modelo.Profesor;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class ProfesorDAO {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void guardar(Profesor profesor) {
        //se inicia la sesion
        Session session = sessionFactory.openSession();
        //la transaccion a relizar
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            //guardamos el usuario
            session.persist(profesor);
           
            tx.commit();
        }
        catch (Exception e) {
            //Se regresa a un estado consistente 
            if (tx!=null){ 
                tx.rollback();
            }
            e.printStackTrace(); 
        } finally {
            //cerramos simpre la sesion
            session.close();
        }
    }
        
        
            /**
     * Elimina el usuario de la base de datos
     * @param profesor el usuario a eliminar
     */
    public void eliminar(Profesor profesor) {
        Session session = sessionFactory.openSession();
        //la transaccion a relizar
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            //eliminamos el usuario
            session.delete(profesor);
           
            tx.commit();
        }
        catch (Exception e) {
            //Se regresa a un estado consistente 
            if (tx!=null){ 
                tx.rollback();
            }
            e.printStackTrace(); 
        }
        finally {
            //cerramos siempre la sesion
            session.close();
        }
    }
    
    
    
    
    /**
     * Actualiza el usuario en la base de datos
     * @param profesor con los nuevos valores 
     */
    public void actualizar(Profesor profesor) {
        Session session = sessionFactory.openSession();
        //la transaccion a relizar
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            //actualizar el usuario
            session.update(profesor);
           
            tx.commit();
        }
        catch (Exception e) {
            //Se regresa a un estado consistente 
            if (tx!=null){ 
                tx.rollback();
            }
            e.printStackTrace(); 
        }
        finally {
            //cerramos siempre la sesion
            session.close();
        }
}
    
    
        /**
     * Regresa la lista de todos los usuarios en la base de datos
     * @return la lista que contiene a todos los usuarios de la base de datos
     */
    public List<Profesor> getProfesores(){
        List<Profesor> result= null;
        Session session = sessionFactory.openSession();
        Transaction tx=null;
        try{
            tx=session.beginTransaction();
            String hql= "FROM Profesor";
            Query query =session.createQuery(hql);
            result=(List<Profesor>)query.list();
            tx.commit();
        }catch (Exception e){
            if(tx != null)
                tx.rollback();
            e.printStackTrace();      
        }finally{
            session.close();
        }
        return result;
    }
    
    
       public Profesor getProfesor(int idProfesor) {
        Profesor result = null;
        Session s = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = s.beginTransaction();
            String hql = "FROM Profesor WHERE idProfesor = :idProfesor";                  
            Query query = s.createQuery(hql);
            query.setParameter("idProfesor",idProfesor);
            result = (Profesor)query.uniqueResult();
            tx.commit();
        }catch(Exception e){
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
        }finally{
            s.close();
        }
        return result;
    }    
}