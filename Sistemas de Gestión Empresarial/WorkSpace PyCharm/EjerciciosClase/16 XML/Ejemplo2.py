from xml.dom import minidom

arbol_dom = minidom.parse('objeto.xml')
lista = arbol_dom.getElementsByTagName("objeto")
nodo = lista[0]
print(nodo.tagName)
print(nodo.getAttribute("nombre"))
print(nodo.firstChild.data)