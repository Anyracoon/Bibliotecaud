package co.edu.universidad.biblioteca.modelo;

/**
* Contrato que cumple todo material que puede prestarse.
* Una interfaz define QUE se puede hacer, no COMO se hace.
*/

public interface Prestable {
	
	/** Marca el material como prestado. */
	void prestar();
	
	/** Marca el material como disponible otra vez. */
	void devolver();
	
	/** @return true si el material se puede prestar en este momento. */
	boolean estaDisponible();
	
	/** @return cantidad maxima de dias que se puede tener el material. */
	int diasMaximoPrestamo();
}