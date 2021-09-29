package detencionHilos;

public class DetenerHilo extends Thread {

	private boolean stopHilo = false;

	public void pararHilo() {
		stopHilo = true;
	}

	@Override
	public void run() {
		while (!stopHilo) {
			System.out.println("En el hilo");
		}
	}

	public static void main(String[] args) {
		DetenerHilo h = new DetenerHilo();
		h.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		h.pararHilo();
	}

}
