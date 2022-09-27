package Persistencia;

import entidades.Libro;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class LibrosDAO {
    
    
     static EntityManager em = Persistence.createEntityManagerFactory("LIBRERIAPU").createEntityManager();
     public void guardar(Libro libro){
        em.getTransaction().begin();
        em.persist(libro);
        em.getTransaction().commit();
     }
}
