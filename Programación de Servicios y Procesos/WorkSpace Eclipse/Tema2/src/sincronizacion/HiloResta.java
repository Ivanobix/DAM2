package sincronizacion;
public class HiloResta extends Thread {

	private Contador contador;

	public HiloResta(String nombre, Contador contador) {
		setName(nombre);
		this.contador = contador;
	}

	@Override
	public void run() {
		synchronized (contador) {
			for (int i = 0; i < 300; i++) {
				contador.decrementarContador();
			}
			System.out.println(getName() + " --> " + contador.getContador());
		}

	}

}