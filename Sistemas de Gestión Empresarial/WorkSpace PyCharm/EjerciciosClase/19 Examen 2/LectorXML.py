from xml.dom import minidom


def obtenerAlbumes():
    infoFichero = obtenerLineaConfiguracion(1)
    fichero = infoFichero[0] + "\\" + infoFichero[1]
    infoFichero = obtenerLineaConfiguracion(2)
    elementosABuscar = infoFichero[0]
    arbol_dom = minidom.parse(fichero)
    return arbol_dom.getElementsByTagName(str(elementosABuscar))


def obtenerLineaConfiguracion(lineaABuscar):
    aDevolver = []
    with open("config.cfg", 'r', encoding='utf-8') as fh:
        contadorLineas = 1
        for line in fh:
            if contadorLineas == lineaABuscar:
                aDevolver = line.split(",")
                break
            contadorLineas += 1
    return aDevolver


def escribirAlbumEnFichero(nombreFichero, lineas, sobreescribir):
    metodo = 'a'
    if sobreescribir:
        metodo = 'w'
    with open(nombreFichero, metodo, encoding='utf-8') as fh:
        for linea in lineas:
            aEscribir = ""
            for elemento in linea:
                aEscribir += elemento + ", "
            aEscribir = aEscribir[:-2] + "\n"
            fh.write(aEscribir)


def obtenerInfoAlbum(album):
    informacionAlbum = []
    informacionAlbum.append(album.getAttribute("titulo"))
    informacionAlbum.append(album.getAttribute("fechaPublicacion"))
    return informacionAlbum


def obtenerInfoPersonajes(album):
    informacionPersonajes = []
    personajes = album.getElementsByTagName("personaje")
    for personaje in personajes:
        perso = personaje.firstChild.data + " (" + personaje.getAttribute("especie") + ")"
        informacionPersonajes.append(perso)
    return informacionPersonajes


def obtenerInfoDibujante(album):
    informacionDibujante = []
    dibujante = album.getElementsByTagName("dibujante")
    informacionDibujante.append(dibujante[0].getElementsByTagName("nombre")[0].firstChild.data)
    informacionDibujante.append(dibujante[0].getElementsByTagName("fechas")[0].getAttribute("nacimiento"))
    informacionDibujante.append(dibujante[0].getElementsByTagName("pais")[0].firstChild.data)
    return informacionDibujante


def obtenerInfoGuionista(album):
    informacionGuionista = []
    guionista = album.getElementsByTagName("guionista")
    informacionGuionista.append(guionista[0].getElementsByTagName("nombre")[0].firstChild.data)
    informacionGuionista.append(guionista[0].getElementsByTagName("fechas")[0].getAttribute("nacimiento"))
    informacionGuionista.append(guionista[0].getElementsByTagName("pais")[0].firstChild.data)
    return informacionGuionista


def guardarInfoEnFicheros(album, contadorFicheros):
    albumAGuardar = []

    albumAGuardar.append(obtenerInfoAlbum(album))
    albumAGuardar.append(obtenerInfoPersonajes(album))
    albumAGuardar.append(obtenerInfoDibujante(album))
    albumAGuardar.append(obtenerInfoGuionista(album))

    escribirAlbumEnFichero("album_" + str(contadorFicheros) + ".txt", albumAGuardar, True)


def mostrarInfoAlbum(album):
    infoAlbum = obtenerLineaConfiguracion(2)
    aMostrar = ""
    for atributo in range(len(infoAlbum) - 1):
        aMostrar += album.getAttribute(infoAlbum[atributo + 1].replace("\n", "")) + ", "
    print(aMostrar[:-2])

    infoAlbum = obtenerLineaConfiguracion(3)
    personajes = album.getElementsByTagName("personaje")
    aMostrar = ""
    for personaje in personajes:
        aMostrar += personaje.firstChild.data + ": "
        for atributo in range(len(infoAlbum) - 1):
            aMostrar += personaje.getAttribute(infoAlbum[atributo + 1].replace("\n", "")) + ", "
    print(aMostrar[:-2])

    print("--------------------------")


if __name__ == '__main__':
    contadorFicheros = 1
    for album in obtenerAlbumes():
        guardarInfoEnFicheros(album, contadorFicheros)
        mostrarInfoAlbum(album)
        contadorFicheros += 1
