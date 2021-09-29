from Avion import Avion;
from Camion import Camion;
from Coche import Coche;
from Moto import Moto;

tipoVehiculo = "";

with open('C:\\Users\\SGE\Desktop\\Iván_García_Prieto 12\\flota.txt', 'r', encoding='utf-8') as fh:
    for contenido in fh:
        lineaSeparada = contenido.split(",");
        if len(lineaSeparada) == 1:
            if lineaSeparada[0] != "\n":
                tipoVehiculo = lineaSeparada[0].replace("\n", "").upper();

        else:
            lineaSeparada[len(lineaSeparada) - 1] = lineaSeparada[len(lineaSeparada) - 1].replace("\n", "");
            if tipoVehiculo == "AVION":
                subsonico = False;
                if lineaSeparada[4] == "Si":
                    subsonico = True;
                avion = Avion(lineaSeparada[0], lineaSeparada[1], lineaSeparada[2], int(lineaSeparada[3]), subsonico,
                              int(lineaSeparada[5]));
                print(avion.toString());

            elif tipoVehiculo == "MOTO":
                moto = Moto(lineaSeparada[0], lineaSeparada[1], lineaSeparada[2], lineaSeparada[3],
                            int(lineaSeparada[4]));
                print(moto.toString());

            elif tipoVehiculo == "COCHE":
                turbo = False;
                if lineaSeparada[5] == "Si":
                    turbo = True;
                coche = Coche(lineaSeparada[0], lineaSeparada[1], lineaSeparada[2], int(lineaSeparada[3]),
                              lineaSeparada[4], turbo);
                print(coche.toString());

            else:
                asistenciaConduccion = False;
                tacografo = False;
                if lineaSeparada[4] == "Si":
                    asistenciaConduccion = True;
                if lineaSeparada[5] == "Si":
                    tacografo = True;
                camion = Camion(lineaSeparada[0], lineaSeparada[1], lineaSeparada[2], int(lineaSeparada[3]),
                                asistenciaConduccion, tacografo);
                print(camion.toString());

            print("\n----------\n");
