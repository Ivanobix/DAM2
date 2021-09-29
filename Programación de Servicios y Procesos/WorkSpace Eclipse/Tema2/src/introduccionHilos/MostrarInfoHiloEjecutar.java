package introduccionHilos;

public class MostrarInfoHiloEjecutar {

	public static void main(String[] args) {

		Thread principal = Thread.currentThread();
		principal.setName("Principal");

		System.out.println("Nombre principal: " + principal.getName());
		System.out.println("Información principal: " + principal.toString());
		System.out.println("ID Principal: " + principal.getId());
		System.out.println("\n-------------------------");
		System.out.println("--- Creamos los hilos ---");
		System.out.println("-------------------------\n");

		ThreadGroup grupoHilos = new ThreadGroup("hilosPSP");

		for (int i = 1; i < 4; i++) {
			MostrarInfoHilo hilo = new MostrarInfoHilo(grupoHilos, "HILO " + i);
			hilo.setPriority(i);
			hilo.start();
			try {
				hilo.join(); // Detiene la ejecución del programa hasta que la ejecución del hilo termina
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
