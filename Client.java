import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        
        final String HOST = "127.0.0.1";
        final int PUERTO = 5000;

        DataInputStream in;
        DataOutputStream out;

        try {
            Socket sc = new Socket(HOST, PUERTO);
            in = new DataInputStream(sc.getInputStream());
            out = new DataOutputStream(sc.getOutputStream());
            out.writeInt(1);
            int mensaje = in.readInt();
            System.out.println(mensaje);
            sc.close();
        }
        catch (IOException ex) {
            System.out.println("Error Cliente");
        }
    }
}
