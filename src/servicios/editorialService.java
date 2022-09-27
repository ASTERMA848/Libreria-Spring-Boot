package servicios;

import entidades.Editorial;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class editorialService {

    Scanner leer = new Scanner(System.in).useDelimiter("\n");
    static EntityManager em = Persistence.createEntityManagerFactory("LIBRERIAPU").createEntityManager();

    public void CrearEditorial() {
        try {
            Editorial editorial = new Editorial();
            System.out.print("Ingresar el nombre de la editorial");
            editorial.setNombre(leer.next());
            em.getTransaction().begin();
            em.persist(editorial);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Ocurrio un error inesperado, volver a intentar");
            CrearEditorial();
        }
    }

    public void BorrarEditorial() {
        try {
            System.out.print("Ingresar el ID de la editorial que desea borrar");
            Editorial editorial = em.find(Editorial.class, leer.nextInt());
            em.getTransaction().begin();
            em.remove(editorial);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("El ID ingresado es invalido, volver a intentarlo");
            BorrarEditorial();
        }
    }

    public void ModificarEditorial() {
        try {
            System.out.print("Ingresar el ID de la editorial el cual desea modificar: ");
            Editorial editorial = em.find(Editorial.class, leer.nextInt());
            System.out.print("Ingresar el nuevo nombre");
            editorial.setNombre(leer.next());
            em.getTransaction().begin();
            em.merge(editorial);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("El ID ingresado es invalido, volver a intentarlo");
            ModificarEditorial();
        }

    }

    public void alta_bajaEditorial() {
        try {
            System.out.println("Ingrese 1 si quiere dar de baja \n"
                    + "Ingrese 2 si quiere dar de alta");
            int eleccion = leer.nextInt();
            switch (eleccion) {
                case 1:
                    System.out.print("Ingrese el ID de la editorial que desea dar de baja: ");
                    Editorial editorial = em.find(Editorial.class, leer.nextInt());
                    if (editorial.getAlta() == false) {
                        System.out.println("Esta editorial ya esta dada de baja");
                    } else {
                        editorial.setAlta(false);
                        em.getTransaction().begin();
                        em.merge(editorial);
                        em.getTransaction().commit();
                    }
                    break;

                case 2:
                    System.out.print("Ingrese el ID de la editorial que desea dar de alta: ");
                    Editorial editoriall = em.find(Editorial.class, leer.nextInt());
                    if (editoriall.getAlta() == true) {
                        System.out.println("Esta editorial ya esta dada de alta");
                    } else {
                        editoriall.setAlta(true);
                        em.getTransaction().begin();
                        em.merge(editoriall);
                        em.getTransaction().commit();
                    }
                    break;

            }
        } catch (Exception e) {
            System.out.println("El ID es invalido, volver a intentar");
            alta_bajaEditorial();
        }
    }
}
