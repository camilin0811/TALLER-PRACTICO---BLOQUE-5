import java.time.LocalDate;

/**
 * Actividad 5.3 - Prueba minima.
 *
 * No es una aplicacion completa: solo el fragmento necesario para validar
 * las decisiones de diseno. Ejecuta una renovacion VALIDA y una INVALIDA
 * para demostrar que Prestamo protege su regla de negocio.
 */
public class PruebaSmartLibrary {

    public static void main(String[] args) {

        // ------------------------------------------------------------------
        // Montaje del escenario
        // ------------------------------------------------------------------
        Estudiante estudiante = new Estudiante(
                "1094567890", "Camilo Restrepo", "camilo@universidad.edu.co",
                "EST-2024-118", "Ingenieria de Sistemas");

        Bibliotecario bibliotecario = new Bibliotecario(
                "43876211", "Marta Ochoa", "marta@biblioteca.edu.co",
                "EMP-045", "Manana");

        Libro libro = new Libro("978-0132350884", "Clean Code", "Robert C. Martin");

        // El ejemplar SOLO puede nacer desde el libro: composicion.
        Ejemplar ejemplar = libro.registrarEjemplar("EJ-00123");

        Prestamo prestamo = new Prestamo(
                estudiante, ejemplar,
                LocalDate.of(2026, 9, 21),      // fecha del prestamo
                LocalDate.of(2026, 10, 5));     // fecha prevista de devolucion

        // ------------------------------------------------------------------
        // PRUEBA 1: renovacion valida
        // ------------------------------------------------------------------
        System.out.println("=== PRUEBA 1: renovacion VALIDA ===");
        System.out.println("Fecha prevista inicial : " + prestamo.getFechaPrevistaDevolucion());

        prestamo.renovar(LocalDate.of(2026, 10, 15));

        estudiante.notificar("Su prestamo de '" + libro.getTitulo() + "' fue renovado.");
        bibliotecario.notificar("Renovacion registrada para el ejemplar "
                              + ejemplar.getCodigoBarras() + ".");

        System.out.println("Nueva fecha prevista   : " + prestamo.getFechaPrevistaDevolucion());
        System.out.println("Cantidad renovaciones  : " + prestamo.cantidadRenovaciones());
        System.out.println("Historial              : " + prestamo.getRenovaciones().get(0));

        // ------------------------------------------------------------------
        // PRUEBA 2: renovacion invalida
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("=== PRUEBA 2: renovacion INVALIDA ===");
        try {
            // Se intenta renovar hacia una fecha ANTERIOR a la vigente.
            prestamo.renovar(LocalDate.of(2026, 10, 1));
            System.out.println("ERROR: la renovacion invalida fue aceptada.");
        } catch (IllegalArgumentException e) {
            System.out.println("Rechazada correctamente -> " + e.getMessage());
        }

        // El estado del prestamo NO cambio: la operacion es atomica.
        System.out.println("Fecha prevista tras el intento : "
                         + prestamo.getFechaPrevistaDevolucion());
        System.out.println("Cantidad renovaciones          : "
                         + prestamo.cantidadRenovaciones());

        // ------------------------------------------------------------------
        // Comprobacion adicional del polimorfismo de la interfaz
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("=== Contrato Notificable: misma promesa, medios distintos ===");
        Notificable[] destinatarios = { estudiante, bibliotecario };
        for (Notificable destinatario : destinatarios) {
            // Se invoca sin saber si es Estudiante o Bibliotecario.
            destinatario.notificar("La biblioteca cierra a las 6:00 p.m.");
        }

        System.out.println();
        System.out.println("=== Roles declarados por la jerarquia ===");
        Usuario[] usuarios = { estudiante, bibliotecario };
        for (Usuario usuario : usuarios) {
            System.out.println(usuario.getNombre() + " -> " + usuario.descripcionRol());
        }
    }
}
