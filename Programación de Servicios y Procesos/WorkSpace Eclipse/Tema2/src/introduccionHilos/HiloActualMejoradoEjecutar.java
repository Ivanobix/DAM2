package introduccionHilos;

public class HiloActualMejoradoEjecutar {

	public static void main(String[] args) {
		HiloActualMejorado hilo1 = new HiloActualMejorado();
		hilo1.setName("Hilo 1");
		hilo1.start();

		HiloActualMejorado hilo2 = new HiloActualMejorado("Hilo 2");
		hilo2.start();
	}
}
