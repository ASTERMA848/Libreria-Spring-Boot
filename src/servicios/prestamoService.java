package servicios;

import entidades.Cliente;
import entidades.Libro;
import entidades.Prestamo;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class prestamoService {

    static EntityManager em = Persistence.createEntityManagerFactory("LIBRERIAPU").createEntityManager();
    Scanner leer = new Scanner(System.in).useDelimiter("\n");

    public void PrestamoLibro() {
        try {
            libroService librito = new libroService();
            System.out.println("Esta es la lista de libros disponibles: ");
            librito.MostrarLibros();
            System.out.println("Ingrese el numero ISBN del libro el cual desee retirar: ");
            Libro libro = em.find(Libro.class, leer.nextLong());
            if (libro.getEjemplaresRestantes() == 0) {
                System.out.println("Este libro no se puede prestar ya que no hay disponibles");
            } else {
                libro.setEjemplaresRestantes(libro.getEjemplaresRestantes() - 1);
                libro.setEjemplaresPrestados(libro.getEjemplaresPrestados() + 1);
            }

            if (libro.getEjemplaresRestantes() == 0) {
                libro.setAlta(false);
            }

            em.getTransaction().begin();
            em.merge(libro);
            em.getTransaction().commit();

            Prestamo prestamo = new Prestamo();
            Date fecha1 = new Date();
            prestamo.setFechaPrestamo(fecha1);
            System.out.print("Ingrese el ID del cliente que desea llevarse ese libro: ");
            Cliente cliente = em.find(Cliente.class, leer.nextInt());
            prestamo.setCliente(cliente);

            prestamo.setLibro(libro);

            em.getTransaction().begin();
            em.persist(prestamo);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Ocurrio un error, volver a intentar");
            PrestamoLibro();

        }
    }

    public void DevolucionLibro() {
        try {
            System.out.println("Ingrese el numero ISBN del libro que desea devolver: ");
            Libro libro = em.find(Libro.class, leer.nextLong());

            if (libro.getEjemplaresPrestados() > 0) {
                System.out.println(libro);
                libro.setEjemplaresPrestados(libro.getEjemplaresPrestados() - 1);
                libro.setEjemplaresRestantes(libro.getEjemplaresRestantes() + 1);
                libro.setAlta(true);
            } else {
                System.out.println("Este libro no se puede devolver");
                throw new Exception();

            }
            em.getTransaction().begin();
            em.merge(libro);
            em.getTransaction().commit();

            System.out.print("Ingrese el ID del prestamo: ");
            Prestamo prestamo = em.find(Prestamo.class, leer.nextInt());
            Date fecha2 = new Date();

            if (prestamo.getFechaDevolucion() == null) {
                prestamo.setFechaDevolucion(fecha2);
                em.getTransaction().begin();
                em.merge(prestamo);
                em.getTransaction().commit();
            } else {
                System.out.println("Este libro ya ha sido devuelto");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Ocurrio un error, volver a intentar");
            DevolucionLibro();
        }
    }

    public void consultarPrestamo() {
        try {

            List<Prestamo> prestamos = (List<Prestamo>) em.createQuery("SELECT p From Prestamo p").getResultList();

            System.out.print("Ingrese el ID del cliente para saber sobre sus prestamos: ");
            Integer ID = leer.nextInt();

            for (Prestamo pres : prestamos) {
                if (pres.getCliente().getID() == ID) {
                    System.out.println(pres);
                } else {
                    System.out.println("No existe un cliente con ese ID, volver a intenar");
                    consultarPrestamo();
                }

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Ocurrio un error, volver a intenar");
            consultarPrestamo();

        }

    }
}
