/**
 * Segunda especializacion de Usuario.
 *
 * Cumple el mismo contrato Notificable que Estudiante, pero por un medio
 * distinto. Esa diferencia es intencional: demuestra que la interfaz
 * garantiza QUE se puede notificar, nunca COMO se notifica.
 */
public class Bibliotecario extends Usuario implements Notificable {

    private String codigoEmpleado;
    private String turno;

    public Bibliotecario(String identificacion, String nombre, String correo,
                         String codigoEmpleado, String turno) {
        super(identificacion, nombre, correo);
        this.codigoEmpleado = codigoEmpleado;
        this.turno = turno;
    }

    public String getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public String getTurno() {
        return turno;
    }

    @Override
    public String descripcionRol() {
        return "Bibliotecario del turno " + turno;
    }

    @Override
    public void notificar(String mensaje) {
        // Misma promesa que Estudiante, medio de entrega diferente.
        System.out.println("[PANEL INTERNO - empleado " + codigoEmpleado + "] " + mensaje);
    }
}
