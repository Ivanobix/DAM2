package ordenacion;

public class QuickShortNumeros {

	public static void main(String[] args) {
		int numeros[] = {12,6,23,90,3};
		quicksort(numeros, 0, numeros.length - 1);
		for (int i = 0; i<numeros.length;i++) {
			System.out.println(numeros[i]);
		}
		
	}
	
	private static int particion(int numeros[], int izquierda, int derecha) {
        int pivote = numeros[izquierda];
        while (true) {
            while (numeros[izquierda] < pivote) {
                izquierda++;
            }
            while (numeros[derecha] > pivote) {
                derecha--;
            }
            if (izquierda >= derecha) {
                return derecha;
            } else {
                int temporal = numeros[izquierda];
                numeros[izquierda] = numeros[derecha];
                numeros[derecha] = temporal;
                izquierda++;
                derecha--;
            }
        }
    }
	
	private static void quicksort(int arreglo[], int izquierda, int derecha) {
	    if (izquierda < derecha) {
	        int indiceParticion = particion(arreglo, izquierda, derecha);
	        quicksort(arreglo, izquierda, indiceParticion);
	        quicksort(arreglo, indiceParticion + 1, derecha);
	    }
	}

}
