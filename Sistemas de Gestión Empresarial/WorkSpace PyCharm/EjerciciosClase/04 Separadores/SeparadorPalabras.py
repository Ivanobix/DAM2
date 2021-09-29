palabra = "";
while palabra != "*":
    palabra = input("Introduce una palabra: ");
    if (palabra != "*"):
        numero = int(input("Introduce en cuánto quieres dividir la palabra: "));
        letrasSeparacion = len(palabra) // numero;
        aDevolver = "";
        for i in range(numero - 1):
            aDevolver += palabra[:letrasSeparacion] + " ";
            palabra = palabra[letrasSeparacion:];
        aDevolver += palabra;
        print(aDevolver);
