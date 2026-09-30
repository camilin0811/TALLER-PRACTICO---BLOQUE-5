import java.time.LocalDate;

/**
 * Lado "PARTE" de la composicion Prestamo (1) *-- (0..*) Renovacion.
 *
 * R11: "un prestamo registra las renovaciones realizadas. Cada renovacion
 * conserva la fecha anterior de devolucion, la nueva fecha y la fecha en que
 * se realizo la renovacion".
 *
 * Los tres atributos son final y no hay metodos set: una renovacion es un
 * HECHO HISTORICO y, una vez registrada, no debe poder alterarse.
 */
public class Renovacion {

    private final LocalDate fechaRenovacion;   // cuando se realizo el tramite
    private final LocalDate fechaAnterior;     // fecha de devolucion que tenia antes
    private final LocalDate nuevaFecha;        // fecha de devolucion que queda vigente

    /**
     * Constructor de paquete: una Renovacion solo puede crearla el Prestamo
     * al que pertenece, nunca codigo externo. Es la composicion hecha cumplir.
     */
    Renovacion(LocalDate fechaAnterior, LocalDate nuevaFecha, LocalDate fechaRenovacion) {
        this.fechaAnterior = fechaAnterior;
        this.nuevaFecha = nuevaFecha;
        this.fechaRenovacion = fechaRenovacion;
    }

    public LocalDate getFechaRenovacion() {
        return fechaRenovacion;
    }

    public LocalDate getFechaAnterior() {
        return fechaAnterior;
    }

    public LocalDate getNuevaFecha() {
        return nuevaFecha;
    }

    @Override
    public String toString() {
        return "Renovacion del " + fechaRenovacion
             + ": " + fechaAnterior + " -> " + nuevaFecha;
    }
}
