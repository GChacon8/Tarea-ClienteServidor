import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * Clase Servidor que contiene la logica para la comunicacion con el cliente.
 * @author Gabriel Chacon Alfaro
 */
public class Servidor {
    /**
     * Clase del servidor que espera una interaccion en su puerto con algun cliente.
     * Devuelve el monto solicitado por el cliente usando la clase Mensaje.
     * @param args - no recibe parametros (vacio)
     */
    public static void main(String[] args) {
        ServerSocket servidor = null;
        Socket sc = null;
        DataInputStream in;
        DataOutputStream out;
        final int PUERTO = 5000;

        try {
            servidor = new ServerSocket(PUERTO);
            System.out.println("Servidor iniciado");

            while(true) {
                sc = servidor.accept();
                System.out.println("Cliente conectado");
                in = new DataInputStream(sc.getInputStream());
                out = new DataOutputStream(sc.getOutputStream());   
                String strDelCliente = in.readUTF(); 
                System.out.println("Solicitud recibida: " + strDelCliente);
                Mensaje msgDelCliente = Mensaje.fromString(strDelCliente);
                Mensaje respuesta = msgDelCliente.calculate();
                out.writeUTF(respuesta.toString());
                System.out.println("Respuesta enviada: " + respuesta.toString());
                sc.close();
                System.out.println("Cliente desconectado");
            }
        } 
        catch (IOException ex) {
            System.out.println("Error Server");
        }
    }
}
