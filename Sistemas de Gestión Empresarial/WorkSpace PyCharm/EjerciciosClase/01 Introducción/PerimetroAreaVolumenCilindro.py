diametro = float(input("Diámetro: "));
altura = float(input("Altura: "));

perimetro = 2 * 3.14 * (diametro / 2);
area = (3.14 * ((diametro / 2)) ** 2);
volumen = 3.14 * (((diametro / 2)) ** 2) * altura;

print("Perímetro: " + str(perimetro) + ".");
print("Área: " + str(area) + ".");
print("Volumen: " + str(volumen) + ".");
