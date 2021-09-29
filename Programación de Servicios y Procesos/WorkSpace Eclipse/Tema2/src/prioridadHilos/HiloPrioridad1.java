package prioridadHilos;
import java.util.ArrayList;

public class HiloPrioridad1 extends Thread {

	private long c = 0;
	private boolean stopHilo = false;

	public long getContador() {
		return c;
	}

	public void pararHilo() {
		stopHilo = true;
	}

	public void run() {
		while (!stopHilo) {
			c++;
		}
	}

	public static void main(String[] args) {
		ArrayList<HiloPrioridad1> hilos = new ArrayList<>();

		int prioridad = MIN_PRIORITY;

		for (int i = 1; i <= 3; i++) {
			hilos.add(new HiloPrioridad1());

			if (i == 2)
				prioridad = NORM_PRIORITY;
			else if (i == 3)
				prioridad = MAX_PRIORITY;

			hilos.get(i - 1).setPriority(prioridad);
			hilos.get(i - 1).start();
		}

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		for (HiloPrioridad1 hilo : hilos) {
			hilo.pararHilo();
			System.out.println("Hilo con prioridad " + hilo.getPriority() + ": " + hilo.getContador());
		}

	}
};
