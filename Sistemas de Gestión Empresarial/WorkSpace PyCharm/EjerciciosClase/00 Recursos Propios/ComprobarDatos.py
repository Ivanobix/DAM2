# Saber si una cadena comienza con una subcadena determinada
cadena = "Esto es una prueba"
print(cadena.startswith("prueba"))
# Saber si una cadena finaliza con una subcadena determinada
print(cadena.endswith("prueba"))

# Saber si una cadena es alfanumérica
print(cadena.isalnum())
# Saber si una cadena es alfabética
print(cadena.isalpha())
# Saber si una cadena es numérica
print(cadena.isdigit())

# Saber si una cadena contiene solo minúsculas
print(cadena.islower())
# Saber si una cadena contiene solo mayúsculas
print(cadena.isupper())

# Eliminar elementos repetidos de una lista
# Recuerda que se desordena todo
lista = [2, 4, 4, 4, 4, 4, 9, 9]
print(list(set(lista)))
