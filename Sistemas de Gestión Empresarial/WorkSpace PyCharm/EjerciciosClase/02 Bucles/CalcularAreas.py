print("1: Rectángulo, 2: Cuadrado, 3: Paralelogramo, 4: Triángulo, 5: Trapecio, 6: Polígono Regular");
figura = int(input("¿Qué área quieres calcular?: "));
if figura == 1:
    base = float(input("Introduce la base: "));
    altura = float(input("Introduce la altura: "));
    resultado = base * altura;
    print("El área del rectángulo es: " + str(resultado));

elif figura == 2:
    lado = float(input("Introduce el lado: "));
    resultado = lado ** 2;
    print("El área del cuadrado es: " + str(resultado));

elif figura == 3:
    base = float(input("Introduce la base: "));
    altura = float(input("Introduce la altura: "));
    resultado = base * altura;
    print("El área del paralelogramo es: " + str(resultado));

elif figura == 4:
    base = float(input("Introduce la base: "));
    altura = float(input("Introduce la altura: "));
    resultado = (base * altura) / 2;
    print("El área del triángulo es: " + str(resultado));

elif figura == 5:
    baseInferior = float(input("Introduce la base inferior: "));
    baseSuperior = float(input("Introduce la base superior: "));
    altura = float(input("Introduce la altura: "));
    resultado = ((baseSuperior + baseInferior) / 2) * altura;
    print("El área del trapecio es: " + str(resultado));

elif figura == 6:
    perimetro = float(input("Introduce el perimetro: "));
    radio = float(input("Introduce el radio: "));
    resultado = (perimetro * radio) / 2;
    print("El área del polígono regular es: " + str(resultado));
else:
    print("El dato introducido no es válido.");
