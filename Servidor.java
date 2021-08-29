import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
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
                int mensaje = in.readInt(); 
                System.out.println(mensaje);
                out.writeInt(0);
                sc.close();
                System.out.println("Cliente desconectado");
            }
        } 
        catch (IOException ex) {
            System.out.println("Error Server");
        }
    } 
        
}
