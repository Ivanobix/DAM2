public class Cuenta {

	private int saldo;

	public Cuenta(int s) {
		saldo = s;
	}

	public int getSaldo() {
		return saldo;
	}

	public void restar(int cantidad) {
		saldo = saldo - cantidad;
	}

	public void RetirarDinero(int cant, String nom) {
		if (getSaldo() >= cant) {
			System.out.println(nom + " VA RETIRAR SALDO (ACTUAL ES:" + getSaldo() + ")");
			try {
				Thread.sleep(500);
			} catch (InterruptedException ex) {
			}
			restar(cant);
			System.out.println("\t" + nom + " retira -> " + cant + " ACTUAL(" + getSaldo() + ")");
		} else {
			System.out.println(nom + " no puede retirar dinero, NO HAY SALDO (" + getSaldo() + ")");
		}

		if (getSaldo() < 0)
			System.out.println("SALDO NEGATIVO => " + getSaldo());
	}// RetirarDinero
}// Cuenta
