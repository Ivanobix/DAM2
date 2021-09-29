package introduccionHilos;
public class NombreHiloEjecutar {

	public static void main(String[] args) {

		System.out.println("3 HILOS INICIADOS\n");

		NombreHilo hilo1 = new NombreHilo("Hilo 1");
		hilo1.start();

		NombreHilo hilo2 = new NombreHilo("Hilo 2");
		hilo2.start();

		NombreHilo hilo3 = new NombreHilo("Hilo 3");
		hilo3.start();

	}
}
