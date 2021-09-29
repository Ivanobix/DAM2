import java.io.IOException;

public class Ejercicio11C {

	public static void main(String[] args) throws IOException {
		ProcessBuilder pb = new ProcessBuilder();
		pb.command("C:\\Program Files\\WindowsApps\\Microsoft.Office.Desktop.Word_16051.13328.20292.0_x86__8wekyb3d8bbwe\\Office16\\WINWORD.EXE");
		Process p = pb.start();
		System.out.println("El PID del proceso Microsoft Word es: " + p.pid());

	}

}
