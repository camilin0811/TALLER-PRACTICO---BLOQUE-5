import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Lado "TODO" de la composicion Libro (1) *-- (0..*) Ejemplar.
 *
 * R10: "un libro puede tener varios ejemplares fisicos; cada ejemplar
 * corresponde a un unico libro".
 *
 * La composicion no se expresa solo con tener una lista. Se hace cumplir con
 * tres decisiones combinadas:
 *   1. Ejemplar no tiene constructor publico: solo nace desde registrarEjemplar.
 *   2. La referencia del ejemplar a su libro es final: nunca se reasigna.
 *   3. La lista se devuelve inmodificable: nadie inserta ni elimina partes
 *      desde fuera del todo.
 */
public class Libro {

    private String isbn;
    private String titulo;
    private String autor;
    private final List<Ejemplar> ejemplares = new ArrayList<>();

    public Libro(String isbn, String titulo, String autor) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
    }

    /**
     * Unico camino para crear un ejemplar: garantiza que ninguna copia
     * fisica pueda existir sin la obra catalogada que le da identidad.
     */
    public Ejemplar registrarEjemplar(String codigoBarras) {
        Ejemplar ejemplar = new Ejemplar(codigoBarras, this);
        ejemplares.add(ejemplar);
        return ejemplar;
    }

    public List<Ejemplar> getEjemplares() {
        return Collections.unmodifiableList(ejemplares);
    }

    public int cantidadEjemplares() {
        return ejemplares.size();
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }
}
