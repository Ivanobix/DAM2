package introduccionHilos;

public class HiloActualMejorado extends Thread {

	public HiloActualMejorado() {
		super();
	}

	public HiloActualMejorado(String nombreDelHilo) {
		super(nombreDelHilo);
	}

	public void run() {
		System.out.println("Resumen: " + toString());
		System.out.println("Nombre : " + getName());
		System.out.println("N�mero de hilos activos en el grupo actual: " + activeCount());
	}
}
