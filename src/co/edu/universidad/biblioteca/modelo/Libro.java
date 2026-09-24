package co.edu.universidad.biblioteca.modelo;

public class Libro extends Material {
    private String autor;
    private int numeroPaginas;

    public Libro(String codigo, String titulo, int anioPublicacion, String autor, int numeroPaginas) {
        super(codigo, titulo, anioPublicacion);
        this.autor = autor;
        this.numeroPaginas = numeroPaginas;
    }

    @Override
    public int diasMaximoPrestamo() {
        return 15;
    }

    @Override
    public String getTipo() {
        return "Libro";
    }

    @Override
    public String toString() {
        return super.toString() + " | Autor: " + autor;
    }
}