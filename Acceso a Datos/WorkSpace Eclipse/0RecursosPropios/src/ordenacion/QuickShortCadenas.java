package ordenacion;

public class QuickShortCadenas {

	public static void main(String[] args) {
		String cadenas[] = {"hola", "adios", "pepe", "zz", "aaa", "áz"};
		quicksort(cadenas, 0, cadenas.length - 1);
		for (int i = 0; i< cadenas.length;i++) {
			System.out.println(cadenas[i]);
		}
	}
	
	private static int particion(String cadenas[], int izquierda, int derecha) {
	    String pivote = cadenas[izquierda];
	    while (true) {
	        while (cadenas[izquierda].compareTo(pivote) < 0) {
	            izquierda++;
	        }
	        while (cadenas[derecha].compareTo(pivote) > 0) {
	            derecha--;
	        }
	        if (izquierda >= derecha) {
	            return derecha;
	        } else {
	            String temporal = cadenas[izquierda];
	            cadenas[izquierda] = cadenas[derecha];
	            cadenas[derecha] = temporal;
	            izquierda++;
	            derecha--;
	        }
	    }
	}
	
	private static void quicksort(String arreglo[], int izquierda, int derecha) {
	    if (izquierda < derecha) {
	        int indiceParticion = particion(arreglo, izquierda, derecha);
	        quicksort(arreglo, izquierda, indiceParticion);
	        quicksort(arreglo, indiceParticion + 1, derecha);
	    }
	}

}
