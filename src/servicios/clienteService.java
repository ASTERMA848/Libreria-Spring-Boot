package servicios;

import entidades.Cliente;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class clienteService {

    static EntityManager en = Persistence.createEntityManagerFactory("LIBRERIAPU").createEntityManager();
    Scanner leer = new Scanner(System.in).useDelimiter("\n");

    public void CrearCliente() {

        try {
            Cliente c = new Cliente();
            System.out.print("Ingresar el nombre del cliente: ");
            c.setNombre(leer.next());
            System.out.print("Ingresar el apellido del cliente: ");
            c.setApellido(leer.next());
            System.out.print("Ingresar el documento del cliente: ");
            c.setDoc(leer.nextLong());
            System.out.print("Ingresar el numero de telefono del cliente: ");
            c.setTelefono(leer.next());
            en.getTransaction().begin();
            en.persist(c);
            en.getTransaction().commit();

        } catch (Exception e) {
            //System.out.println("Ocurrio un error, volver a intentar");
            System.out.println(e.getMessage());
            //CrearCliente();
        }
    }

    public void ModificarCliente() {
        try {
            System.out.print("Ingrese el ID del cliente que desee modificar: ");
            Cliente cliente = en.find(Cliente.class, leer.nextInt());

            System.out.print("Que desea modificar?: \n"
                    + "1: Nombre \n"
                    + "2: Apellido \n"
                    + "3: Documento: \n"
                    + "4: Celular: ");
            Integer eleccion = leer.nextInt();
            switch (eleccion) {

                case 1:
                    System.out.print("Escriba el nuevo nombre: ");
                    cliente.setNombre(leer.next());
                    en.getTransaction().begin();
                    en.persist(cliente);
                    en.getTransaction().commit();
                    break;

                case 2:
                    System.out.print("Ingrese el nuevo apellido: ");
                    cliente.setApellido(leer.next());
                    en.getTransaction().begin();
                    en.persist(cliente);
                    en.getTransaction().commit();
                    break;
                case 3:
                    System.out.print("Ingrese el nuevo documento: ");
                    cliente.setDoc(leer.nextLong());
                    en.getTransaction().begin();
                    en.persist(cliente);
                    en.getTransaction().commit();
                    break;
                case 4:
                    System.out.print("Ingrese el nuevo celular: ");
                    cliente.setTelefono(leer.next());
                    en.getTransaction().begin();
                    en.persist(cliente);
                    en.getTransaction().commit();
                    break;
            }
        } catch (Exception e) {
            System.out.println("Ocurrio un error, por favor volver a intentar");
            System.out.println(e.getMessage());
            ModificarCliente();

        }
    }

    public void BorrarCliente() throws Exception {
        try {

            System.out.print("Ingrese el ID del cliente que desee borrar: ");
            Cliente cliente = en.find(Cliente.class, leer.nextInt());
            en.getTransaction().begin();
            en.remove(cliente);
            en.getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Ocurrio un error, volver a intentar");
            BorrarCliente();
        }

    }
}
