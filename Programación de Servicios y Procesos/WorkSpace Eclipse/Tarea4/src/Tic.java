public class Tic extends Thread {

	@Override
	public void run() {
		while (true) {
			try {
				sleep(1000);
				System.out.println("Tic");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
