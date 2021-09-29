package introduccionHilos;

public class MostrarInfoHilo extends Thread {

	public MostrarInfoHilo(ThreadGroup grupo, String nombre) {
		super(grupo, nombre);
	}

	@Override
	public void run() {
		System.out.println("Información del Hilo: " + toString());
		System.out.println("Dentro del Hilo: " + getName());
		System.out.println("	Prioridad: " + getPriority());
		System.out.println("	ID: " + getId());
		System.out.println("	Grupo: " + getThreadGroup().getName());
		System.out.println("	Hilos activos: " + activeCount() + "\n");
	}
}
