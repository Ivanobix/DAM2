from xml.dom import minidom

arbol_dom = minidom.parse('bookstore.xml')
librerias = arbol_dom.getElementsByTagName("bookstore")

for libreria in librerias:
    print("Tagname: " + libreria.tagName)
    print("Nombre: " + libreria.getAttribute("name"))
    print("Tiene hijos: " + str(libreria.hasChildNodes()))
    print("------------------------------------------------------")

    libros = libreria.getElementsByTagName("book")
    for libro in libros:
        print("Tagname: " + libro.tagName)
        print("Categoría: " + libro.getAttribute("category"))

        titulo = libro.getElementsByTagName("title")
        print("Título: " + titulo[0].firstChild.data)
        ano = libro.getElementsByTagName("year")
        print("Año: " + ano[0].firstChild.data)
        precio = libro.getElementsByTagName("price")
        print("Precio: " + precio[0].firstChild.data)

        escritor = libro.getElementsByTagName("writer")
        print("Escritor: " + escritor[0].firstChild.data)
        resumer = libro.getElementsByTagName("resumer")
        print("Resumer: " + resumer[0].firstChild.data)

        print("-----------------")
