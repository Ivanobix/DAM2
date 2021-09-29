from xml.dom import minidom

arbol_dom = minidom.parse('objeto2.xml')
lista = arbol_dom.getElementsByTagName("objeto")

for obj in lista:
    print(obj.tagName)
    print(obj.getAttribute("nombre"))
    print("Tiene hijos: " + str(obj.hasChildNodes()))

    objetos = obj.getElementsByTagName("lista")
    for l in objetos:
        print("Tagname: " + l.tagName)
        print("Atributo: " + l.getAttribute("nombre"))
        print("Informacion: " + l.firstChild.data)
