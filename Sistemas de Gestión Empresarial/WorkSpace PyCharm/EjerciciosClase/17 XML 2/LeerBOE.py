from xml.dom import minidom

arbol_dom = minidom.parse('boe.xml')
pedirNivel = input("Introduce nivel (1,2,3): ")
nivel = 0
if pedirNivel == "1":
    nivel = arbol_dom.getElementsByTagName("seccion")
elif pedirNivel == "2":
    nivel = arbol_dom.getElementsByTagName("departamento")
else:
    nivel = arbol_dom.getElementsByTagName("epigrafe")

for texto in nivel:
    print(texto.getAttribute("nombre"))
