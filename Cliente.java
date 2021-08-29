import javax.swing.*;  
import java.awt.event.*;  
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente implements ActionListener{  
    JTextField int_1;
    JTextField int_2;
    JTextField int_3;
    JTextArea area;  
    JButton send;  

    Cliente() {  
        JFrame frame = new JFrame("Interfaz de Cliente");
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
    public static void main(String[] args) {  
        new Cliente();
    }  
}  