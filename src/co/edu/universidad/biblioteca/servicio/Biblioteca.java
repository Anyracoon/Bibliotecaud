package co.edu.universidad.biblioteca.servicio;

import co.edu.universidad.biblioteca.modelo.Material;
import co.edu.universidad.biblioteca.modelo.Usuario;
import co.edu.universidad.biblioteca.excepciones.PrestamoException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Biblioteca {
    private String nombre;
    private Map<String, Material> catalogo;
    private Map<String, Usuario> usuarios;

    public Biblioteca(String nombre) {
        this.nombre = nombre;
        this.catalogo = new HashMap<>();
        this.usuarios = new HashMap<>();
    }

    public void registrarMaterial(Material material) {
        catalogo.put(material.getCodigo(), material);
    }

    public void registrarUsuario(Usuario usuario) {
        usuarios.put(usuario.getDocumento(), usuario);
    }

    private Usuario buscarUsuario(String documento) throws PrestamoException {
        if (!usuarios.containsKey(documento)) {
            throw new PrestamoException("No existe el usuario con documento " + documento);
        }
        return usuarios.get(documento);
    }

    private Material buscarMaterial(String codigo) throws PrestamoException {
        if (!catalogo.containsKey(codigo)) {
            throw new PrestamoException("No existe el material con codigo " + codigo);
        }
        return catalogo.get(codigo);
    }

    public void prestar(String documento, String codigo) throws PrestamoException {
        Usuario usuario = buscarUsuario(documento);
        Material material = buscarMaterial(codigo);

        if (!usuario.puedePrestarMas()) {
            throw new PrestamoException(usuario.getNombre() + " ya tiene el maximo de " + Usuario.MAX_PRESTAMOS + " prestamos.");
        }
        if (!material.estaDisponible()) {
            throw new PrestamoException("'" + material.getTitulo() + "' ya esta prestado.");
        }

        material.prestar();
        usuario.agregarPrestamo(material);
    }

    public void devolver(String documento, String codigo) throws PrestamoException {
        Usuario usuario = buscarUsuario(documento);
        Material material = buscarMaterial(codigo);

        if (!usuario.getPrestamos().contains(material)) {
            throw new PrestamoException(usuario.getNombre() + " no tiene prestado '" + material.getTitulo() + "'.");
        }

        material.devolver();
        usuario.quitarPrestamo(material);
    }

    public void listarCatalogo() {
        Map<String, Material> ordenado = new TreeMap<>(catalogo);
        for (Material m : ordenado.values()) {
            System.out.println(m);
        }
    }

    public void listarDisponibles() {
        Map<String, Material> ordenado = new TreeMap<>(catalogo);
        for (Material m : ordenado.values()) {
            if (m.estaDisponible()) {
                System.out.println(m);
            }
        }
    }
}
