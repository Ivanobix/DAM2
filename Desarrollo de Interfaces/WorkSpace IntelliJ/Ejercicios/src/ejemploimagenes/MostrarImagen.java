package ejemploimagenes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Locale;

public class MostrarImagen {
    private JPanel panel1;
    private JButton button1;
    private JLabel lbl1;
    private JLabel lbl2;

    public MostrarImagen() {
        JFrame frame = new JFrame("MostrarImagen");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                File directorio = new File("images");
                directorio.mkdir();



                JFileChooser selector = new JFileChooser();
                int opt = selector.showOpenDialog(null);

                if(opt == JFileChooser.APPROVE_OPTION) {
                    File ficherOrigen = selector.getSelectedFile();

                    File ficheroDestino = new File(  "images/" + ficherOrigen.getName());

                    try {
                        Files.copy(ficherOrigen.toPath(), ficheroDestino.toPath(), StandardCopyOption.REPLACE_EXISTING);


                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                   //La ajusto a 30x40
                    ImageIcon iconOriginal = new ImageIcon(ficheroDestino.getPath());

                    Image imgOriginal = iconOriginal.getImage();

                    Image imgEscalada = imgOriginal.getScaledInstance(30, 40, Image.SCALE_FAST);

                    ImageIcon iconEscalado = new ImageIcon(imgEscalada);

                   lbl1.setIcon(iconEscalado);
                   lbl2.setText(ficheroDestino.getPath());



                }









            }
        });
    }

    public static void main(String[] args) {
        //Locale.setDefault(Locale.US);
        new MostrarImagen();
    }
}
