while True:
    x = input("Ingrese un número('*' para terminar): ");
    if x == "*":
        break;
    else:
        nx = int(x);
        if nx > 0:
            print("Número positivo");
        elif nx < 0:
            print("Número negativo");
        else:
            print("Es cero");
