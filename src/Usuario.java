/**
 * Superclase de la generalizacion evaluada en la Actividad 3.
 *
 * Se declara ABSTRACTA porque en SmartLibrary no existe ninguna persona
 * que sea "solo usuario": toda persona real es estudiante o bibliotecario.
 * Instanciar Usuario directamente no representaria nada del dominio.
 *
 * La herencia NO se justifica por los tres atributos repetidos de R12,
 * sino porque un estudiante y un bibliotecario pueden sustituir a un
 * usuario en cualquier contexto donde el sistema espere uno.
 */
public abstract class Usuario {

    private String identificacion;
    private String nombre;
    private String correo;

    public Usuario(String identificacion, String nombre, String correo) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.correo = correo;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    /**
     * Obliga a que cada especializacion declare que es realmente.
     * Impide que la jerarquia se reduzca a compartir tres campos.
     */
    public abstract String descripcionRol();
}
