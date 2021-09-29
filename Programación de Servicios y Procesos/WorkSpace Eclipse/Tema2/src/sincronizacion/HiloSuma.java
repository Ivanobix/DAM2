package sincronizacion;

public class HiloSuma extends Thread {

	private Contador contador;

	public HiloSuma(String nombre, Contador contador) {
		setName(nombre);
		this.contador = contador;
	}

	@Override
	public void run() {
		synchronized (contador) {
			for (int i = 0; i < 300; i++) {
				contador.incrementarContador();
			}
			System.out.println(getName() + " --> " + contador.getContador());
		}

	}

}
