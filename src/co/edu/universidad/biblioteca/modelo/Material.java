package co.edu.universidad.biblioteca.modelo;

/**
 * Clase base abstracta para cualquier material de la biblioteca.
 */
public abstract class Material implements Prestable {
    private final String codigo;
    private String titulo;
    private int anioPublicacion;
    private boolean prestado;

    public Material(String codigo, String titulo, int anioPublicacion) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El codigo no puede ser nulo o vacio");
        }
        this.codigo = codigo;
        this.titulo = titulo;
        this.anioPublicacion = anioPublicacion;
        this.prestado = false;
    }

    @Override
    public void prestar() {
        this.prestado = true;
    }

    @Override
    public void devolver() {
        this.prestado = false;
    }

    @Override
    public boolean estaDisponible() {
        return !this.prestado;
    }

    public abstract String getTipo();

    public String getCodigo() { return codigo; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public int getAnioPublicacion() { return anioPublicacion; }
    public boolean isPrestado() { return prestado; }

    @Override
    public String toString() {
        return String.format("[%s] %-8s %-35s (%d) %s", 
            codigo, getTipo(), titulo, anioPublicacion, prestado ? "Prestado" : "Disponible");
    }
}