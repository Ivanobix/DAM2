public class Tac extends Thread {

	@Override
	public void run() {
		while (true) {
			try {
				sleep(1000);
				System.out.println("Tac");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
