package sincronizacion;

public class IniciarContadores {

	public static void main(String[] args) {
		// Este ejercicio usa la sincronizacion de hilos con synchronized
		Contador contador = new Contador(0);
		HiloSuma hiloSuma = new HiloSuma("Suma", contador);
		HiloResta hiloResta = new HiloResta("Resta", contador);

		hiloSuma.start();
		hiloResta.start();

	}

}
