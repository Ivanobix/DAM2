#VERSIÓN SIMPLE
texto = "32sdflohs3"
cifra = {
    's': 'a',
    'k': 'j',
    'd': '2',
    'a': 'j'
}

texto_cifrado = []
for letra in texto:
    texto_cifrado.append(cifra.get(letra, letra))

texto_cifrado = "".join(texto_cifrado)


#VERSIÓN AVANZADA

texto = "32sdflohs3"
cifra = {
 'S': 'E',
 'E': 'S',
 'O': 'H',
 'G': 'C'
}

texto_cifrado = "".join(cifra.get(letra, letra) for letra in texto)