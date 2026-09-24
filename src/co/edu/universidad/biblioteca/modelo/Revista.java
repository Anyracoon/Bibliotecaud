package co.edu.universidad.biblioteca.modelo;

public class Revista extends Material {
    private int numeroEdicion;

    public Revista(String codigo, String titulo, int anioPublicacion, int numeroEdicion) {
        super(codigo, titulo, anioPublicacion);
        this.numeroEdicion = numeroEdicion;
    }

    @Override
    public int diasMaximoPrestamo() {
        return 5;
    }

    @Override
    public String getTipo() {
        return "Revista";
    }

    @Override
    public String toString() {
        return super.toString() + " | Edicion No. " + numeroEdicion;
    }
}