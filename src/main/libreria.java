package main;

import java.util.Scanner;
import servicios.autorService;
import servicios.clienteService;
import servicios.editorialService;
import servicios.libroService;
import servicios.prestamoService;

public class libreria {

    public static void main(String[] args) throws Exception {
        Scanner leer = new Scanner(System.in).useDelimiter("\n");
        autorService a = new autorService();
        libroService l = new libroService();
        clienteService c = new clienteService();
        editorialService e = new editorialService();
        prestamoService p = new prestamoService();

        int res = 0;

        do {
            System.out.println("----- BIENVENIDO AL MENU DE LIBRERIA -----");
            System.out.println("OPCIONES: \n"
                    + "1: Crear un autor \n"
                    + "2: Borrar un autor \n"
                    + "3: Dar de alta o baja a un autor \n"
                    + "4: Modificar un autor \n"
                    + "5: Buscar un autor por su nombre \n"
                    + "6:Crear un Libro \n"
                    + "7: Borrar un Libro \n"
                    + "8: Modificar Libro \n"
                    + "9: Buscar Libro por ISBN \n"
                    + "10: Buscar Libro por Titulo \n"
                    + "11: Buscar Libro por Autor \n"
                    + "12: Buscar Libro por Editorial \n"
                    + "13: Mostrar Libros \n"
                    + "14: Crear Editorial \n"
                    + "15: Borrar Editorial \n"
                    + "16: Modificar Editorial \n"
                    + "17: Dar de alta/baja a una Editorial \n"
                    + "18: Crear Cliente \n"
                    + "19: Modificar Cliente \n"
                    + "20: Borrar Cliente \n"
                    + "21: Prestamo Libro \n"
                    + "22: Devolucion Libro \n"
                    + "23: Consultar Prestamo\n");

            System.out.print("Que desea hacer: ");
            res = leer.nextInt();

            switch (res) {
                case 1:
                    a.CrearAutor();
                    break;
                case 2:
                    a.BorrarAutor();
                    break;
                case 3:
                    a.alta_bajaAutor();
                    break;
                case 4:
                    a.ModificarAutor();
                    break;
                case 5:
                    a.BuscarAutorPorNombre();
                    break;
                case 6:
                    l.CrearLibro();
                    break;
                case 7:
                    l.BorrarLibro();
                    break;
                case 8:
                    l.ModificarLibro();
                    break;
                case 9:
                    l.BuscarLibroPorISBN();
                    break;
                case 10:
                    l.BuscarLibroPortTitulo();
                    break;
                case 11:
                    l.BuscarLibroPorAutor();
                    break;
                case 12:
                    l.BuscarLibroEditorial();
                    break;
                case 13:
                    l.MostrarLibros();
                    break;
                case 14:
                    e.CrearEditorial();
                    break;
                case 15:
                    e.BorrarEditorial();
                    break;
                case 16:
                    e.ModificarEditorial();
                    break;
                case 17:
                    e.alta_bajaEditorial();
                    break;
                case 18:
                    c.CrearCliente();
                    break;
                case 19:
                    c.ModificarCliente();
                    break;
                case 20:
                    c.BorrarCliente();
                    break;
                case 21:
                    p.PrestamoLibro();
                    break;
                case 22:
                    p.DevolucionLibro();
                    break;
                case 23:
                    p.consultarPrestamo();
                    break;
            }

            System.out.println("Desea seguir S/N:");
            String respuesta = leer.next();
            if ("N".equals(respuesta) || "n".equals(respuesta)) {
                res = 30;
            }

        } while (res != 30);

    }
}
