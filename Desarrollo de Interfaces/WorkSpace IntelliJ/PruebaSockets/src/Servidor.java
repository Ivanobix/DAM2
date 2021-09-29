import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor implements ActionListener, Runnable {
    private JPanel contentPane;
    private JTextArea txtConsola;
    private JButton btnEnviar;

    public Servidor() {
        JFrame frame = new JFrame("Servidor");
        frame.setContentPane(contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        initHandlers();
        Thread miHilo = new Thread(this);
        miHilo.start();
    }

    private void initHandlers() {
        btnEnviar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("enviar")) {
            System.out.println("Servidor");
        }
    }

    @Override
    public void run() {
        try {
            ServerSocket servidor = new ServerSocket(5050);
            while (true) {
                Socket miSocket = servidor.accept();
                DataInputStream flujoDeEntrada = new DataInputStream(miSocket.getInputStream());
                String mensaje = flujoDeEntrada.readUTF();
                txtConsola.append("\n" + mensaje);
                DataOutputStream flujoDeSalida = new DataOutputStream(miSocket.getOutputStream());
                flujoDeSalida.writeUTF("Devuelto: " + mensaje);
                miSocket.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
