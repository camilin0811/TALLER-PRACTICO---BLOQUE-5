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
        System.out.println("[PANEL INTERNO - empleado " + codigoEmpleado + "] " + mensaje);
    }
}
