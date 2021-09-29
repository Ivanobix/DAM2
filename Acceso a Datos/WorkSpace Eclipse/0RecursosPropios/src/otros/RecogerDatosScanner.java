package otros;
import java.util.Scanner;

public class RecogerDatosScanner {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String cadena = "";
		boolean parar = false;
		while (!parar) {
			System.out.println("Introduce la cadena: ");
			cadena = sc.nextLine();
			if (!cadena.isEmpty()) {
				parar = true;
				sc.close();
			}else {
				System.out.println(cadena);
			}
			
		}
	}
	
}
