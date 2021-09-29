package introduccionHilos;

public class HiloActual extends Thread {

	public void run() {
		System.out.println("Dentro del Hilo : " + Thread.currentThread().getName());
	}
}
