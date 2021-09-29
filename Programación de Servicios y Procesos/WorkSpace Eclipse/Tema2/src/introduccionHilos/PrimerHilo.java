package introduccionHilos;

public class PrimerHilo extends Thread {

	private int x;

	public static void main(String[] args) {
		PrimerHilo primerHilo = new PrimerHilo(10);
		primerHilo.start();
	}

	public PrimerHilo(int x) {
		this.x = x;
	}

	public void run() {
		for (int i = 1; i <= x; i++) {
			System.out.println(i);
		}
	}
}
