package introduccionHilos;
public class NombreHilo extends Thread {

	public NombreHilo(String nombreDelHilo) {
		super(nombreDelHilo);
	}

	public void run() {
		for (int i = 1; i <= 5; i++) {
			System.out.println("Hilo: " + getName() + " C = " + i);
		}
	}

}
