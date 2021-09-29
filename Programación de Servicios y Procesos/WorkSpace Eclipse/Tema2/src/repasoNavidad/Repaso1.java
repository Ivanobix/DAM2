package repasoNavidad;

public class Repaso1 extends Thread {

	@Override
	public void run() {
		System.out.println("Hola Mundo");
		System.out.println("Identificador: " + getId());
	}

}
