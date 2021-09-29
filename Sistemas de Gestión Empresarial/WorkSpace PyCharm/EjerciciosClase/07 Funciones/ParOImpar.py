def es_par_o_impar(numero):
    aDevolver = "El número " + str(numero) + " es ";
    if (numero % 2 == 0):
        aDevolver += "par.";
    else:
        aDevolver += "impar.";
    return aDevolver;


if __name__ == '__main__':
    print(es_par_o_impar(int(input("Introduce un número: "))));
