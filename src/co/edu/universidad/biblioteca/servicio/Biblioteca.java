package co.edu.universidad.biblioteca.modelo;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    public static final int MAX_PRESTAMOS = 3;
    private String documento;
    private String nombre;
    private List<Material> prestamos;

    public Usuario(String documento, String nombre) {
        this.documento = documento;
        this.nombre = nombre;
        this.prestamos = new ArrayList<>();
    }

    public boolean puedePrestarMas() {
        return prestamos.size() < MAX_PRESTAMOS;
    }

    public void agregarPrestamo(Material material) {
        prestamos.add(material);
    }

    public boolean quitarPrestamo(Material material) {
        return prestamos.remove(material);
    }

    public List<Material> getPrestamos() {
        return new ArrayList<>(prestamos); // Copia defensiva
    }

    public String getDocumento() { return documento; }
    public String getNombre() { return nombre; }

    @Override
    public String toString() {
        return nombre + " (doc. " + documento + ") prestamos: " + prestamos.size() + "/" + MAX_PRESTAMOS;
    }
}