import java.time.LocalDate;

public class PruebaSmartLibrary {

    public static void main(String[] args) {

        Estudiante estudiante = new Estudiante(
                "1094567890", "Camilo Restrepo", "camilo@universidad.edu.co",
                "EST-2024-118", "Ingenieria de Sistemas");

        Bibliotecario bibliotecario = new Bibliotecario(
                "43876211", "Marta Ochoa", "marta@biblioteca.edu.co",
                "EMP-045", "Manana");

        Libro libro = new Libro("978-0132350884", "Clean Code", "Robert C. Martin");

        Ejemplar ejemplar = libro.registrarEjemplar("EJ-00123");

        Prestamo prestamo = new Prestamo(
                estudiante, ejemplar,
                LocalDate.of(2026, 9, 21),
                LocalDate.of(2026, 10, 5));

        System.out.println("=== PRUEBA 1: renovacion VALIDA ===");
        System.out.println("Fecha prevista inicial : " + prestamo.getFechaPrevistaDevolucion());

        prestamo.renovar(LocalDate.of(2026, 10, 15));

        estudiante.notificar("Su prestamo de '" + libro.getTitulo() + "' fue renovado.");
        bibliotecario.notificar("Renovacion registrada para el ejemplar "
                              + ejemplar.getCodigoBarras() + ".");

        System.out.println("Nueva fecha prevista   : " + prestamo.getFechaPrevistaDevolucion());
        System.out.println("Cantidad renovaciones  : " + prestamo.cantidadRenovaciones());
        System.out.println("Historial              : " + prestamo.getRenovaciones().get(0));

        System.out.println();
        System.out.println("=== PRUEBA 2: renovacion INVALIDA ===");
        try {
            prestamo.renovar(LocalDate.of(2026, 10, 1));
            System.out.println("ERROR: la renovacion invalida fue aceptada.");
        } catch (IllegalArgumentException e) {
            System.out.println("Rechazada correctamente -> " + e.getMessage());
        }

        System.out.println("Fecha prevista tras el intento : "
                         + prestamo.getFechaPrevistaDevolucion());
        System.out.println("Cantidad renovaciones          : "
                         + prestamo.cantidadRenovaciones());

        System.out.println();
        System.out.println("=== Contrato Notificable: misma promesa, medios distintos ===");
        Notificable[] destinatarios = { estudiante, bibliotecario };
        for (Notificable destinatario : destinatarios) {
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
