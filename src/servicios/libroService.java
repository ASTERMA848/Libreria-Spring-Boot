package servicios;

import Persistencia.LibrosDAO;
import entidades.Autor;
import entidades.Editorial;
import entidades.Libro;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class libroService extends LibrosDAO {

    static EntityManager em = Persistence.createEntityManagerFactory("LIBRERIAPU").createEntityManager();
    Scanner leer = new Scanner(System.in).useDelimiter("\n");

    public void CrearLibro() throws Exception {

        Libro libro = new Libro();
        try {

            System.out.print("Ingresar el titulo del Libro: ");
            libro.setTitulo(leer.next());

            System.out.print("Ingresar el año: ");
            libro.setAnio(leer.nextInt());

            System.out.print("Ingrese la cantidad de ejemplares: ");
            libro.setEjemplares(leer.nextInt());
            libro.setEjemplaresRestantes(libro.getEjemplares());

            System.out.print("Ingrese el Id del Autor: ");
            Autor autor = (Autor) em.find(Autor.class, leer.nextInt());
            libro.setAutor(autor);
            if (autor == null) {
                throw new Exception("No existe un autor con ese ID");
            }

            System.out.print("Ingresar el ID de la Editorial: ");
            Editorial editorial = (Editorial) em.find(Editorial.class, leer.nextInt());
            libro.setEditorial(editorial);

            if (editorial == null) {
                throw new Exception("No existe una editorial con esa ID");
            }

            guardar(libro);
            
//            em.getTransaction().begin();
//            em.persist(libro);
//            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e + "\n Volver a intentar");

            CrearLibro();
        }

    }

    public void BorrarLibro() {
        try {
            System.out.print("Ingresar el ISBN del libro que desea borrar");
            Libro libro = em.find(Libro.class, leer.nextLong());
            em.getTransaction().begin();
            em.remove(libro);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Numero ISBN mal ingresado, volver a intentar");
            BorrarLibro();
        }
    }

    public void ModificarLibro() {
        try {
            System.out.print("Ingresar el ISBN del libro el cual desea modificar");
            Libro libro = em.find(Libro.class, leer.nextLong());
            System.out.print("Ingresar el nuevo titulo");
            libro.setTitulo(leer.next());
            em.getTransaction().begin();
            em.merge(libro);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Numero ISBN mal ingresado, volver a intentar");
            ModificarLibro();
        }
    }

    public void LibroDevueltos() {
        try {
            System.out.println("Ingrese el numero ISBN del libro que desea devolver: ");
            Libro libro = em.find(Libro.class, leer.nextLong());

            if (libro.getEjemplaresPrestados() > 0) {
                System.out.println(libro);
                libro.setEjemplaresPrestados(libro.getEjemplaresPrestados() - 1);
                libro.setEjemplaresRestantes(libro.getEjemplaresRestantes() + 1);
                libro.setAlta(true);
                System.out.println("---- LIBRO DEVUELTO ---- ");
            } else {
                System.out.println("Este libro no se puede devolver");
            }
            em.getTransaction().begin();
            em.merge(libro);
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Ocurrio un error inesperado, volver a intentar" + e.getMessage());
            LibroDevueltos();
        }

    }

    public void BuscarLibroPorISBN() {
        try {
            System.out.println("Ingresar el numero ISBN del libro que desea buscar: ");
            Libro libro = (Libro) em.createQuery("SELECT l "
                    + "FROM Libro l"
                    + " Where l.isbn = :num").setParameter("num", leer.nextLong()).getSingleResult();

            System.out.println(libro);
        } catch (Exception e) {
            System.out.println("Numero ISBN mal ingresado, volver a intentar");
            BuscarLibroPorISBN();

        }
    }

    public void BuscarLibroPortTitulo() {
        try {
            System.out.print("Ingresar el titulo del libro que desea buscar: ");
            String nombre = leer.next();
            List<Libro> libro = (List<Libro>) em.createQuery("SELECT l FROM Libro l WHERE l.titulo LIKE :nombre").setParameter("nombre", "%" + nombre + "%").getResultList();

            if (libro.isEmpty()) {
                System.out.println("No existe un libro con el nombre " + nombre + " volver a intentar");
                BuscarLibroPortTitulo();
            } else {
                for (Libro li : libro) {
                    System.out.println(li);
                }

            }
        } catch (Exception e) {
            System.out.println("Ocurrio un error inesperado, volver a intenar");
            System.out.println(e.getMessage());
            BuscarLibroPortTitulo();
        }
    }

    public void BuscarLibroPorAutor() {
        try {
            System.out.print("Ingresar el nombre del autor para buscar algun libro: ");
            String nombre = leer.next();
            List<Libro> libro = (List<Libro>) em.createQuery("SELECT l FROM Libro l WHERE l.autor.nombre LIKE :nombre").setParameter("nombre", "%" + nombre + "%").getResultList();

            if (libro.isEmpty()) {
                System.out.println("No existen Libros en donde el autor sea " + nombre);
                System.out.println("Volver a intentar");
                BuscarLibroPorAutor();
            } else {
                for (Libro l : libro) {
                    System.out.println(l);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Ocurrio un error inesperado, volver a intentar");
            BuscarLibroPorAutor();

        }
    }

    public void BuscarLibroEditorial() {
        try {
            System.out.print("Ingresar el nombre de la editorial para buscar un libro: ");
            String nombre = leer.next();
            List<Libro> libros = (List<Libro>) em.createQuery("SELECT e FROM Libro e"
                    + " WHERE e.editorial.nombre LIKE :nombre").setParameter("nombre", "%" + nombre + "%").getResultList();
            if (libros.isEmpty()) {
                System.out.println("No existen libros en donde la editorial sea " + nombre);
                System.out.println("Volver a intentar");
                BuscarLibroEditorial();
            } else {
                for (Libro libro : libros) {
                    System.out.println(libro);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Ocurrio un error inesperado, volver a intentar");
            BuscarLibroEditorial();

        }
    }

    public void MostrarLibros() {
        List<Libro> libros = (List<Libro>) em.createQuery("SELECT i FROM Libro i ").getResultList();

        for (Libro libro : libros) {
            //if (libro.isAlta() == true) {
            System.out.println(libro);
            // }
        };
    }
}
