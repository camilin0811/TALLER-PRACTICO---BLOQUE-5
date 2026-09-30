public class Ejemplar {

    private String codigoBarras;
    private boolean disponible;
    private final Libro libro;

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
