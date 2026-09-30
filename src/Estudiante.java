/**
 * Especializacion de Usuario que ademas implementa el contrato Notificable.
 *
 * extends Usuario      -> declara QUE ES el objeto (herencia, relacion "es-un")
 * implements Notificable -> declara QUE SABE HACER (contrato de capacidad)
 *
 * Ambas cosas conviven sin conflicto: Java permite extender una sola clase
 * pero implementar varias interfaces.
 */
public class Estudiante extends Usuario implements Notificable {

    private String codigoEstudiantil;
    private String programaAcademico;

    public Estudiante(String identificacion, String nombre, String correo,
                      String codigoEstudiantil, String programaAcademico) {
        super(identificacion, nombre, correo);      // datos comunes de Usuario
        this.codigoEstudiantil = codigoEstudiantil; // datos propios
        this.programaAcademico = programaAcademico;
    }

    public String getCodigoEstudiantil() {
        return codigoEstudiantil;
    }

    public String getProgramaAcademico() {
        return programaAcademico;
    }

    @Override
    public String descripcionRol() {
        return "Estudiante del programa " + programaAcademico;
    }

    @Override
    public void notificar(String mensaje) {
        // El contrato solo exige recibir el mensaje; el medio lo decide la clase.
        System.out.println("[CORREO -> " + getCorreo() + "] " + mensaje);
    }
}
