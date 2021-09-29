package sincronizacion;

public class Contador {

	private int contador;

	public Contador(int numero) {
		contador = numero;
	}

	public void incrementarContador() {
		contador++;
	}

	public void decrementarContador() {
		contador--;
	}

	public int getContador() {
		return contador;
	}

}
