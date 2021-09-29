public class LeerNombre {

	public static void main(String[] args) {
		if (args.length == 1) {
			System.out.println("Tu nombre es: " + args[0]);
			System.exit(1);
		} else {
			System.exit(-1);
		}
	}
}
