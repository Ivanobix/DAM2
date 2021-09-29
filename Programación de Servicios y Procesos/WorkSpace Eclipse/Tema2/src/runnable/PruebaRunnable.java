package runnable;

public class PruebaRunnable implements Runnable {

	public static void main(String[] args) {
		// Forma 1
		PruebaRunnable pruebaRunnable1 = new PruebaRunnable();
		Thread hilo = new Thread(pruebaRunnable1);
		hilo.start();

		// Forma 2
		Thread hilo2 = new Thread(new PruebaRunnable());
		hilo2.start();

		// Forma 3
		new Thread(new PruebaRunnable()).start();

	}

	@Override
	public void run() {
		System.out.println("Hola Mundo");
		System.out.println("Identificador: " + Thread.currentThread().getId());

	}

}
