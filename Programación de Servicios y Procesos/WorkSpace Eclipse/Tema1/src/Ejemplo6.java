import java.util.Map;

public class Ejemplo6 {

	public static void main(String[] args) {
		//Recuperar variables de entorno
		
		ProcessBuilder test = new ProcessBuilder();
		Map entorno = test.environment();
		System.out.println("Variables de entorno:");
		System.out.println(entorno);

	}

}
