from pyexpat import ExpatError
from xml.dom import minidom

from MiFichero import MiFichero

try:
    arbol_dom = minidom.parse('facturas.xml')
    facturas = arbol_dom.getElementsByTagName("factura")

    with open("facturas.txt", 'w') as fh:
        for factura in facturas:
            fh.write("Tagname: " + factura.tagName + "\n")
            fh.write("Número: " + factura.getAttribute("n_fac") + "\n")
            fh.write("Tiene hijos: " + str(factura.hasChildNodes()) + "\n")
            fh.write("------------------------------------------------------" + "\n")

            fh.write("--------DATOS EMPRESA---------" + "\n")
            datosEmpresa = factura.getElementsByTagName("datos_empresa")
            for datoEmpresa in datosEmpresa:
                fh.write("Tagname: " + datoEmpresa.tagName + "\n")

                nombre = datoEmpresa.getElementsByTagName("nombre")
                fh.write("Nombre: " + nombre[0].firstChild.data + "\n")
                dir = datoEmpresa.getElementsByTagName("dir")
                fh.write("Dirección: " + dir[0].firstChild.data + "\n")

                poblacion = datoEmpresa.getElementsByTagName("poblacion")
                fh.write("Población: " + poblacion[0].firstChild.data + "\n")
                fh.write("Código Postal: " + poblacion[0].getAttribute("cod_postal") + "\n")
                fh.write("Provincia: " + poblacion[0].getAttribute("provincia") + "\n")

                cif = datoEmpresa.getElementsByTagName("cif")
                fh.write("CIF: " + cif[0].firstChild.data + "\n")
                try:
                    telefono = datoEmpresa.getElementsByTagName("telefono")
                    fh.write("Teléfono: " + telefono[0].firstChild.data + "\n")
                except AttributeError:
                    fh.write("Teléfono: n/a" + "\n")

            fh.write("--------DATOS CLIENTE---------" + "\n")
            datosCliente = factura.getElementsByTagName("datos_cliente")
            for datoCliente in datosCliente:
                fh.write("Tagname: " + datoCliente.tagName + "\n")

                fh.write("Número Cliente: " + datoCliente.getAttribute("n_cli") + "\n")

                nombre = datoCliente.getElementsByTagName("nombre")
                fh.write("Nombre: " + nombre[0].firstChild.data + "\n")
                dirEnvio = datoCliente.getElementsByTagName("dir_env")
                fh.write("Dirección de Envío: " + dirEnvio[0].firstChild.data + "\n")
                poblacion = datoCliente.getElementsByTagName("poblacion")
                fh.write("Población: " + poblacion[0].firstChild.data + "\n")
                provincia = datoCliente.getElementsByTagName("provincia")
                fh.write("Provincia: " + provincia[0].firstChild.data + "\n")

            fh.write("--------DATOS FACTURA---------" + "\n")
            datosFactura = factura.getElementsByTagName("datos_factura")
            for datoFactura in datosFactura:
                fh.write("Tagname: " + datoFactura.tagName + "\n")

                fecha = datoFactura.getElementsByTagName("fecha")
                fh.write("Fecha: " + fecha[0].firstChild.data + "\n")

                fh.write("Línea:" + "\n")
                ref = datoFactura.getElementsByTagName("ref")
                fh.write("Referencia: " + ref[0].firstChild.data + "\n")
                desc = datoFactura.getElementsByTagName("desc")
                fh.write("Descuento: " + desc[0].firstChild.data + "\n")
                cant = datoFactura.getElementsByTagName("cant")
                fh.write("Cantidad: " + cant[0].firstChild.data + "\n")
                precio = datoFactura.getElementsByTagName("precio")
                fh.write("Precio: " + precio[0].firstChild.data + "\n")
                importe = datoFactura.getElementsByTagName("importe")
                fh.write("Importe: " + importe[0].firstChild.data + "\n")

                base = datoFactura.getElementsByTagName("base")
                fh.write("Base: " + base[0].firstChild.data + "\n")
                cuotaIva = datoFactura.getElementsByTagName("cuota_iva")
                fh.write("Cuota IVA: " + cuotaIva[0].firstChild.data + "\n")
                total = datoFactura.getElementsByTagName("total")
                fh.write("Total: " + total[0].firstChild.data + "\n")

    mifichero = MiFichero("facturas.txt", False)
    lineas = mifichero.devuelveLineas()
    for linea in lineas:
        print(linea[:-1])

except ExpatError:
    print("El documento contiene errores.")
