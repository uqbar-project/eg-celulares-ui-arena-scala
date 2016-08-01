# Celulares

![celualres](https://cloud.githubusercontent.com/assets/4549002/17305339/5c1120c8-5800-11e6-9f8c-4d8a9dbd8e49.png)

Ejemplo de un A-B-M (Alta-Baja-Modificación) completo de una lista de clientes de una compañía de telefonía celular

## Conceptos a ver

* La pantalla de búsqueda redefine el comportamiento default de una SimpleWindow (mainTemplate) para agregarle la grilla y acciones a la grilla
* Las vistas de edición y nuevo se reutilizan, ya que CrearCelularWindow hereda de EditarCelularWindow
 * También muestra cómo trabajar un Dialog, una ventana modal que toma el control de flujo de la aplicación
 * Al dar de alta o modificar un cliente de la compañía (botón Aceptar) eso refresca la lista de clientes
