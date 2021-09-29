from xml.dom import minidom

arbol_dom = minidom.parse('instituto.xml')
print(arbol_dom.toxml())