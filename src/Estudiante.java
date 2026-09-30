public class Estudiante extends Usuario implements Notificable {

    private String codigoEstudiantil;
    private String programaAcademico;

    public Estudiante(String identificacion, String nombre, String correo,
                      String codigoEstudiantil, String programaAcademico) {
        super(identificacion, nombre, correo);
        this.codigoEstudiantil = codigoEstudiantil;
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
        System.out.println("[CORREO -> " + getCorreo() + "] " + mensaje);
    }
}
