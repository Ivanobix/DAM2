def pedir_operacion():
    operacionValida = False;
    while operacionValida == False:
        try:
            operacion = int(input("Elige una operación: (1 Suma, 2 Resta, 3 Multiplicacion, 4 División): "));
            if operacion <= 4 and operacion >= 1:
                operacionValida = True;
            else:
                print("La operación no existe.");
        except ValueError:
            print("No has introducido un valor válido.");
    return operacion;


def pedir_num(numero):
    while True:
        try:
            num = int(input("Introduce el número " + str(numero) + ": "));
            return num;
        except ValueError:
            print("No has introducido un número.");


def dividir(a, b):
    while True:
        try:
            c = a / b;
        except ZeroDivisionError:
            ex = input("Está dividiendo entre cero, ¿desea cambiar dicho valor? (y/n): ");
            if ex == "y":
                e = pedir_num();
                c = a / e;
            else:
                c = "No se puede realizar la operación.";
        finally:
            return c;


operacion = pedir_operacion();
num1 = pedir_num(1);
num2 = pedir_num(2);

if operacion == 1:
    print(str(num1 + num2));
elif operacion == 2:
    print(str(num1 - num2));
elif operacion == 3:
    print(str(num1 * num2));
else:
    print(str(dividir(num1, num2)));
