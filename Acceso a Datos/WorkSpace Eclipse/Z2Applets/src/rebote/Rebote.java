package rebote;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Rebote extends Applet implements Runnable {
	private static final long serialVersionUID = -4393853409190843426L;
	private final String letra = "I";
	private Thread hilo = null;
	private Font fuente;
	private Button btn;
	private boolean parar;
	private int posicion;
	private boolean haciaDerecha;

	public void init() {
		fuente = new Font("Verdana", Font.BOLD, 26);
		parar = false;
		posicion = 0;
		haciaDerecha = true;
		btn = new Button("DETENER");
		initHandlers();
		add(btn);
	}

	private void initHandlers() {
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (parar) {
					btn.setLabel("DETENER");
					parar = false;
					start();
				} else {
					btn.setLabel("CONTINUAR");
					parar = true;
					stop();
				}

			}
		});
	}

	public void start() {
		if (hilo == null) {
			hilo = new Thread(this);
			hilo.start();
		}
	}

	public void run() {
		Thread hiloActual = Thread.currentThread();
		while (hilo == hiloActual && !parar) {
			if (posicion >= getWidth() - 15)
				haciaDerecha = false;
			else if (posicion <= 0)
				haciaDerecha = true;
			if (haciaDerecha)
				posicion += 3;
			else
				posicion -= 3;
			repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
		}
	}

	public void paint(Graphics g) {
		g.clearRect(1, 1, getSize().width, getSize().height);
		setBackground(Color.yellow);
		g.setFont(fuente);
		g.drawString(letra, posicion, 100);
	}

	public void stop() {
		hilo = null;
	}
}
