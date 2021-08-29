import javax.swing.*;  
import java.awt.event.*;  
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
/**
 * Clase Cliente que contiene la logica para solicitar monto por medio de una interfaz
 * Esta clase utiliza la clase Mensaje para la comunicacion de los datos.
 * @author Gabriel Chacon Alfaro
 */
public class Cliente implements ActionListener{  
    JTextField int_1;
    JTextField int_2;
    JTextField int_3;
    JTextArea area;  
    JButton send;  

    Cliente() {  
        JFrame frame = new JFrame("Interfaz de Cliente");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        area = new JTextArea();
        area.setBounds(20,50,340,130);
        int_1=new JTextField();
        int_1.setBounds(60, 200, 80,20);
        int_2=new JTextField();
        int_2.setBounds(60, 250, 80,20);
        int_3=new JTextField();
        int_3.setBounds(60, 300, 80,20);
        send=new JButton("Send");  
        send.setBounds(200, 300,100,40);
        send.addActionListener(this);
        frame.add(send);
        frame.add(int_1);
        frame.add(int_2); 
        frame.add(int_3);
        frame.add(area);
        frame.setSize(400,400);  
        frame.setLayout(null);  
        frame.setVisible(true);
    }  
    /**
     * Este metodo se ejecuta al presionar el boton de la interfaz.
     * Obtiene los valores enteros ingresados y los manda al server utilizando la clase Mensaje.
     * Al mismo tiempo recibe los datos mandados de vuelta por el server y los muestra en la interfaz.
     */
    public void actionPerformed(ActionEvent e){
        String valor = int_1.getText();  
        String porcentaje = int_2.getText();  
        String peso = int_3.getText();  
        System.out.println(valor + " " + porcentaje + " " + peso); 
        final String HOST = "127.0.0.1";
        final int PUERTO = 5000;
        DataInputStream in;
        DataOutputStream out;
        Mensaje solicitud = new Mensaje(Integer.parseInt(valor),Integer.parseInt(porcentaje),Integer.parseInt(peso));
        area.append(solicitud.toPrint());
        try {
            Socket sc = new Socket(HOST, PUERTO);
            in = new DataInputStream(sc.getInputStream());
            out = new DataOutputStream(sc.getOutputStream());
            out.writeUTF(solicitud.toString());
            String strDelServidor = in.readUTF();
            Mensaje msgDelServidor = Mensaje.fromString(strDelServidor);
            area.append(msgDelServidor.toPrint());
            System.out.println(msgDelServidor.toString());
            sc.close();
        }
        catch (IOException ex) {
            System.out.println("Error Cliente");
        }
    }  
    /**
     * Este metodo main se encarga de instanciar una clase Cliente
     * 
     * @param args - No recibe parametros (vacio)
     */
    public static void main(String[] args) {  
        new Cliente();
    }  
}  