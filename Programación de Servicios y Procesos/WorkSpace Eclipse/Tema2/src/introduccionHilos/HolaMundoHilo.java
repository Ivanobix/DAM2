package introduccionHilos;

public class HolaMundoHilo extends Thread {

	public void run() {
		System.out.println("Hola Mundo Identificador: " + getId());
	}

}
