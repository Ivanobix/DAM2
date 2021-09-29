abc = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ"
cifrado = ""

texto = input("Texto a cifrar: ").upper()
desplazamientos = int(input("Desplazamientos: "))

for letra in texto:
    if letra in abc:
        posicionLetra = abc.index(letra)
        posicionLetraCambiada = (posicionLetra + desplazamientos) % len(abc)
        cifrado += abc[posicionLetraCambiada]
    else:
        cifrado += letra

print("Mensaje cifrado: " + cifrado)
