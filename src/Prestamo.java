import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Clase que concentra las tres decisiones de relacion del taller:
 *
 *   estudiante   -> ASOCIACION  (el estudiante existe antes y despues del prestamo)
 *   ejemplar     -> ASOCIACION  (el ejemplar vuelve a la estanteria al devolverlo)
 *   renovaciones -> COMPOSICION (la renovacion no existe fuera de este prestamo)
 *
 * Tambien protege la regla de renovacion: renovar() valida ANTES de modificar
 * cualquier cosa, de modo que la operacion es atomica. O se cumple completa,
 * o el objeto queda exactamente como estaba.
 */
public class Prestamo {

    private final Estudiante estudiante;                  // ASOCIACION
    private final Ejemplar ejemplar;                      // ASOCIACION
    private final LocalDate fechaPrestamo;

    // Sin metodo set: solo se modifica dentro de renovar(), despues de validar.
    private LocalDate fechaPrevistaDevolucion;

    // COMPOSICION: las partes nacen y mueren con este prestamo.
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

    /**
     * Registra una renovacion siguiendo los cuatro pasos del enunciado:
     *   1. validar que nuevaFecha sea posterior a la fecha actual prevista
     *   2. crear la Renovacion
     *   3. almacenarla
     *   4. actualizar fechaPrevistaDevolucion
     *
     * Si la validacion falla no se ejecuta ninguno de los pasos siguientes:
     * el prestamo no cambia y el historial no se contamina.
     */
    public void renovar(LocalDate nuevaFecha) {

        // 1. Validacion
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

        // 2. Crear la Renovacion conservando la fecha anterior (R11)
        Renovacion renovacion =
            new Renovacion(fechaPrevistaDevolucion, nuevaFecha, LocalDate.now());

        // 3. Almacenarla dentro del prestamo
        renovaciones.add(renovacion);

        // 4. Actualizar la fecha prevista de devolucion
        this.fechaPrevistaDevolucion = nuevaFecha;
    }

    /** Devuelve el ejemplar y lo deja disponible para un nuevo prestamo. */
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
