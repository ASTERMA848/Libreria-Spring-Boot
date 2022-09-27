package servicios;

import entidades.Autor;

import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class autorService {

    static EntityManager em = Persistence.createEntityManagerFactory("LIBRERIAPU").createEntityManager();
    Scanner leer = new Scanner(System.in).useDelimiter("\n");

    public void CrearAutor() throws Exception {
        Autor autor = new Autor();
        try {
            System.out.print("Ingresar el nombre del autor");
            autor.setNombre(leer.next());
            em.getTransaction().begin();
            em.persist(autor);
            em.getTransaction().commit();
        } catch (Exception e) {

            System.out.println("Ocurrio un error, volver a intenar");
            CrearAutor();
        }

    }

    public void BorrarAutor() throws Exception {
        try {
            System.out.print("Ingresar el ID del autor que desea borrar");
            Autor autor = em.find(Autor.class, leer.nextInt());
            if (autor == null) {
                throw new Exception("El ID ingresado es invalido, volver a intentar");
            }
            em.getTransaction().begin();
            em.remove(autor);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
            BorrarAutor();
        }
    }

    public void ModificarAutor() {
        try {

            System.out.print("Ingresar el ID del autor el cual desea modificar");
            Autor autor = em.find(Autor.class, leer.nextInt());
            if (autor == null) {
                throw new Exception("El ID ingresado es falso, volver a intenar");
            }
            System.out.print("Ingresar el nuevo nombre");
            autor.setNombre(leer.next());
            em.getTransaction().begin();
            em.merge(autor);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
            ModificarAutor();
        }
    }

    public void alta_bajaAutor() {
        try {
            System.out.println("Ingrese 1 si quiere dar de baja \n"
                    + "Ingrese 2 si quiere dar de alta");
            int eleccion = leer.nextInt();
            switch (eleccion) {
                case 1:
                    System.out.print("Ingrese el ID del autor que desea dar de baja: ");
                    Autor autor = em.find(Autor.class, leer.nextInt());
                    if (autor.getAlta() == false) {
                        System.out.println("Este autor ya esta dada de baja");
                    } else {
                        autor.setAlta(false);
                        em.getTransaction().begin();
                        em.merge(autor);
                        em.getTransaction().commit();
                    }
                    break;

                case 2:
                    System.out.print("Ingrese el ID del autor que desea dar de alta: ");
                    Autor autorr = em.find(Autor.class, leer.nextInt());
                    if (autorr.getAlta() == true) {
                        System.out.println("Este autor ya esta dada de alta");
                    } else {
                        autorr.setAlta(true);
                        em.getTransaction().begin();
                        em.merge(autorr);
                        em.getTransaction().commit();
                    }
                    break;

            }
        } catch (Exception e) {
            System.out.println("Ocurrio un error, revisar los ID");
            alta_bajaAutor();
        }
    }

    public void BuscarAutorPorNombre() {
        try {
            System.out.println("Ingersar el nombre del autor que desea buscar");
            String nombree = leer.next();

            Autor autor = (Autor) em.createQuery("SELECT a"
                    + " FROM Autor a"
                    + " WHERE a.nombre LIKE :nombree").setParameter("nombree", "%" + nombree + "%").getSingleResult();

            System.out.println(autor);
        } catch (Exception e) {
            System.out.println("Ocurrio un error inesperado, volver a intentar" + e.getMessage());

            BuscarAutorPorNombre();
        }
    }

}
