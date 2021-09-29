package repasoNavidad;

public class Cronometro extends Thread {

	public static void main(String[] args) {
		Cronometro cronometro = new Cronometro();
		cronometro.start();

	}

	@Override
	public void run() {
		try {
			int horas = 0;
			int minutos = 0;
			int segundos = 0;
			while (true) {
				System.out.println(horas + ":" + minutos + ":" + segundos);
				segundos++;
				if (segundos == 60) {
					segundos = 0;
					minutos++;
					if (minutos == 60) {
						minutos = 0;
						horas++;
					}
				}
				sleep(999);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
