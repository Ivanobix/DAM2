import csv
import os

# No hemos usado rutas relativas hasta ahora, pero si se quieren usar absolutas es posible cambiando únicamente el valor de rutaArchivo por la ruta completa
# En mi caso 'C:\Users\SGE\Desktop\Iván_García_Prieto 12'
# Obtener ruta del archivo con el nombre agenda.csv
rutaActual = os.path.dirname(os.path.abspath(__file__));
rutaArchivo = rutaActual + '\\agenda.csv';

# Abrir el archivo para su lectura
with open(rutaArchivo, 'r', encoding='utf-8') as fh:
    # Crear un lector de csv cuyo delimitador es ";"
    lns = csv.reader(fh, delimiter=';');
    # Recorrer el archivo y tratar sus datos;
    for linea in lns:
        nombre = linea[0];
        direccion = linea[1];
        telefono = linea[2];
        print("Nombre: " + nombre + " // Dirección: " + direccion + " // Teléfono: " + telefono);
