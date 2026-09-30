/**
 * Contrato de comportamiento del Bloque 5.
 *
 * Una interfaz NO dice que es un objeto, dice que sabe hacer.
 * R13: "algunos usuarios pueden recibir notificaciones del sistema".
 * La palabra ALGUNOS es la razon por la que este metodo no se declara
 * en la clase Usuario: notificar es una capacidad opcional, no una
 * caracteristica inherente a ser usuario.
 */
public interface Notificable {

    /**
     * Garantiza que el objeto sabe recibir un mensaje de notificacion.
     * NO especifica por que medio se envia: eso lo decide cada clase.
     */
    void notificar(String mensaje);
}
