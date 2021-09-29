package introduccionHilos;

public class HiloActualEjecutar {

	public static void main(String[] args) {
		HiloActual hilo1 = new HiloActual();
		hilo1.start();
		HiloActual hilo2 = new HiloActual();
		hilo2.start();
		HiloActual hilo3 = new HiloActual();
		hilo3.start();
	}

}
