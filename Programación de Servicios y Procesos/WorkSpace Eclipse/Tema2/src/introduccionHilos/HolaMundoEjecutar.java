package introduccionHilos;

public class HolaMundoEjecutar {

	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			HolaMundoHilo nuevoHilo = new HolaMundoHilo();
			nuevoHilo.start();
		}
	}

}
