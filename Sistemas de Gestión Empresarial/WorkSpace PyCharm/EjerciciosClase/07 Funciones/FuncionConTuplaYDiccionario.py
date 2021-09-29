def recorrer_parametros_arbitrarios(parametro_fijo, *arbitrarios, **kwords):
    print(parametro_fijo);
    for argumento in arbitrarios:
        print(argumento);
    for clave in kwords:
        print("El valor de " + clave + " es " + kwords[clave]);


recorrer_parametros_arbitrarios('Fixed', 'arbitrario 1', 'arbitrario 2', 'arbitrario 3', clave1="valor uno",
                                clave2="valor dos");
