import java.time.LocalDate;

public class Renovacion {

    private final LocalDate fechaRenovacion;
    private final LocalDate fechaAnterior;
    private final LocalDate nuevaFecha;

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
