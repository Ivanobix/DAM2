import java.util.concurrent.Semaphore;

public class SacarDinero extends Thread {

	private static final Semaphore semaforo = new Semaphore(1);
	private Cuenta c;

	public SacarDinero(String n, Cuenta c) {
		super(n);
		this.c = c;
	}

	public void run() {
		try {
			semaforo.acquire();

			for (int x = 1; x <= 4; x++) {
				c.RetirarDinero(10, getName());
			}

			semaforo.release();
			
		} catch (Exception e) {
			System.err.println("Algo ha ido mal.");
		}
	}
}
