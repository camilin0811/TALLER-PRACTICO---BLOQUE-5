/**
 * Lado "PARTE" de la composicion con Libro.
 *
 * Copia fisica concreta de una obra. No confundir con Libro: el libro es la
 * obra catalogada (ISBN, titulo, autor) y el ejemplar es lo que efectivamente
 * se presta y se devuelve.
 */
public class Ejemplar {

    private String codigoBarras;
    private boolean disponible;

    // final: cumple R10 ("cada ejemplar corresponde a un UNICO libro").
    // Una vez asignado no puede reasignarse a otra obra.
    private final Libro libro;

    /**
     * Constructor de paquete (sin modificador public) a proposito.
     * Refuerza la composicion: solo Libro.registrarEjemplar puede invocarlo,
     * de modo que es imposible construir un ejemplar huerfano.
     */
    Ejemplar(String codigoBarras, Libro libro) {
        this.codigoBarras = codigoBarras;
        this.libro = libro;
        this.disponible = true;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public Libro getLibro() {
        return libro;
    }

    public boolean estaDisponible() {
        return disponible;
    }

    public void marcarPrestado() {
        this.disponible = false;
    }

    public void marcarDisponible() {
        this.disponible = true;
    }
}
