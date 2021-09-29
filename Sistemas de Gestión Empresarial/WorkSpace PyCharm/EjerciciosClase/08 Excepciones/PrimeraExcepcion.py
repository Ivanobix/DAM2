x = int(input("Introduce un número: "));
y = int(input("Introduce otro número: "));
try:
    z = x / y;
except ZeroDivisionError:
    print("División por cero.");
finally:
    print("Limpiando.");
