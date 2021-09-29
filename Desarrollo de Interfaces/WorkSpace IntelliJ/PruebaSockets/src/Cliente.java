import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente implements ActionListener {
    private JButton btnEnviar;
    private JPanel contentPane;
    private JTextField txtMensaje;


    public Cliente() {
        JFrame frame = new JFrame("Cliente");
        frame.setContentPane(contentPane);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        initHandlers();
    }

    private void initHandlers() {
        btnEnviar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("enviar")) {
            try {
                Socket miSocket = new Socket("localhost", 5050);
                DataOutputStream flujoDeSalida = new DataOutputStream(miSocket.getOutputStream());
                flujoDeSalida.writeUTF(txtMensaje.getText());
                flujoDeSalida.writeInt(23);
                DataInputStream flujoDeEntrada = new DataInputStream(miSocket.getInputStream());
                String mensaje = flujoDeEntrada.readUTF();
                txtMensaje.setText(mensaje);
                miSocket.close();

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
