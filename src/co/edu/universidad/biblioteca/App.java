package co.edu.universidad.biblioteca;

import co.edu.universidad.biblioteca.modelo.Libro;
import co.edu.universidad.biblioteca.modelo.Revista;
import co.edu.universidad.biblioteca.modelo.Material;
import co.edu.universidad.biblioteca.modelo.Usuario;
import co.edu.universidad.biblioteca.servicio.Biblioteca;
import co.edu.universidad.biblioteca.excepciones.PrestamoException;

public class App {
    public static void main(String[] args) {
        Biblioteca biblio = new Biblioteca("Biblioteca Central");

        Material m1 = new Libro("L-001", "Cien anios de soledad", 1967, "Gabriel Garcia Marquez", 417);
        Material m2 = new Libro("L-002", "Clean Code", 2008, "Robert C. Martin", 464);
        Material m3 = new Libro("L-003", "Java: Como programar", 2016, "Deitel y Deitel", 1240);
        Material m4 = new Revista("R-001", "National Geographic", 2024, 312);

        biblio.registrarMaterial(m1);
        biblio.registrarMaterial(m2);
        biblio.registrarMaterial(m3);
        biblio.registrarMaterial(m4);

        Usuario u1 = new Usuario("1001", "Ana Torres");
        Usuario u2 = new Usuario("1002", "Luis Perez");

        biblio.registrarUsuario(u1);
        biblio.registrarUsuario(u2);

        System.out.println("===== CATALOGO INICIAL =====");
        System.out.println("Biblioteca Central");
        biblio.listarCatalogo();

        System.out.println("\n===== PRESTAMOS =====");
        intentarPrestamo(biblio, "1001", "L-001");
        intentarPrestamo(biblio, "1001", "R-001");
        intentarPrestamo(biblio, "1001", "L-002");

        System.out.println("\n===== CASOS DE ERROR =====");
        intentarPrestamo(biblio, "1001", "L-003");
        intentarPrestamo(biblio, "1002", "L-001");
        intentarPrestamo(biblio, "9999", "L-002");
        intentarPrestamo(biblio, "1002", "X-999");

        System.out.println("\n===== DEVOLUCIONES =====");
        try {
            biblio.devolver("1001", "L-001");
            System.out.println("OK -> Ana devolvio L-001");
        } catch (PrestamoException e) {
            System.out.println("ERR -> " + e.getMessage());
        }
        
        try {
            biblio.devolver("1002", "L-002");
            System.out.println("OK -> Luis devolvio L-002");
        } catch (PrestamoException e) {
            System.out.println("ERR -> " + e.getMessage());
        }

        System.out.println("\n===== ESTADO FINAL =====");
        biblio.listarCatalogo();
        mostrarEstadoUsuario(u1);
        mostrarEstadoUsuario(u2);
    }

    private static void intentarPrestamo(Biblioteca biblio, String doc, String cod) {
        try {
            biblio.prestar(doc, cod);
            System.out.println("OK -> " + doc + " presto " + cod);
        } catch (PrestamoException e) {
            System.out.println("ERR -> " + e.getMessage());
        }
    }

    private static void mostrarEstadoUsuario(Usuario u) {
        System.out.println(u);
        for (Material m : u.getPrestamos()) {
            System.out.println("- " + m.getTitulo() + ": maximo " + m.diasMaximoPrestamo() + " dias");
        }
    }
}