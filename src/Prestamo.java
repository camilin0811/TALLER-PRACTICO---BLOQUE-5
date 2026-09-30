import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Prestamo {

    private final Estudiante estudiante;
    private final Ejemplar ejemplar;
    private final LocalDate fechaPrestamo;
    private LocalDate fechaPrevistaDevolucion;
    private final List<Renovacion> renovaciones = new ArrayList<>();

    public static final int MAXIMO_RENOVACIONES = 2;

    public Prestamo(Estudiante estudiante, Ejemplar ejemplar,
                    LocalDate fechaPrestamo, LocalDate fechaPrevistaDevolucion) {
        this.estudiante = estudiante;
        this.ejemplar = ejemplar;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaPrevistaDevolucion = fechaPrevistaDevolucion;
        ejemplar.marcarPrestado();
    }

    public void renovar(LocalDate nuevaFecha) {

        if (nuevaFecha == null || !nuevaFecha.isAfter(fechaPrevistaDevolucion)) {
            throw new IllegalArgumentException(
                "Renovacion invalida: la nueva fecha (" + nuevaFecha + ") debe ser "
              + "posterior a la fecha prevista actual (" + fechaPrevistaDevolucion + ").");
        }

        if (renovaciones.size() >= MAXIMO_RENOVACIONES) {
            throw new IllegalStateException(
                "Renovacion invalida: el prestamo ya alcanzo el maximo de "
              + MAXIMO_RENOVACIONES + " renovaciones.");
        }

        Renovacion renovacion =
            new Renovacion(fechaPrevistaDevolucion, nuevaFecha, LocalDate.now());

        renovaciones.add(renovacion);

        this.fechaPrevistaDevolucion = nuevaFecha;
    }

    public void devolver() {
        ejemplar.marcarDisponible();
    }

    public List<Renovacion> getRenovaciones() {
        return Collections.unmodifiableList(renovaciones);
    }

    public int cantidadRenovaciones() {
        return renovaciones.size();
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public LocalDate getFechaPrevistaDevolucion() {
        return fechaPrevistaDevolucion;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public Ejemplar getEjemplar() {
        return ejemplar;
    }
}
