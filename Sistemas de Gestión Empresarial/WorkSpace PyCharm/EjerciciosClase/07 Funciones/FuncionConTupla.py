def recorrer_parametros_arbitrarios(parametro_fijo, *arbitrarios):
    print(parametro_fijo);
    for argumento in arbitrarios:
        print(argumento);


recorrer_parametros_arbitrarios('Fixed', 'arbitrario 1', 'arbitrario 2', 'arbitrario 3');
