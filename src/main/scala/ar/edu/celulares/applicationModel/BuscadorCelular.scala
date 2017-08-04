package ar.edu.celulares.applicationModel

import ar.edu.celulares.domain.Celular
import java.util.ArrayList
import ar.edu.celulares.home.RepoCelulares
import ar.edu.celulares.home.RepoCelulares
import collection.JavaConversions._
import org.uqbar.commons.model.annotations.Observable

/**
 * Application model que representa la búsqueda de {@link Celular}.
 * Contiene:
 * <ul>
 * 	<li>El estado de los atributos por los cuales buscar: numero y nombre</li>
 *  <li>El comportamiento para realizar la búsqueda (en realidad delega en otros objetos)</li>
 *  <li>El estado del resultado de la búsqueda, es decir que recuerda la lista de Celulares resultado</li>
 *  <li>El estado de la selección de un Celular para poder utilizar el comportamiento que sigue...</li>
 *  <li>Comportamiento para eliminar un Celular seleccionado.</li>
 * </ul>
 *
 * Este es un objeto transiente, que contiene estado de la ejecución para un usuario en particular
 * en una ejecución de la aplicación en particular.
 *
 * @author npasserini
 */
@Observable
class BuscadorCelular extends Serializable {

	var numero : Integer = _
	var nombre : String = _
	var resultados : java.util.List[Celular] = _
	var celularSeleccionado : Celular = _

	// ********************************************************
	// ** Acciones
	// ********************************************************
	def search() = { 
		// WORKAROUND para que refresque la grilla en las actualizaciones
		resultados = new ArrayList[Celular]

		// FIN WORKAROUND
		resultados = RepoCelulares.search(numero, nombre)
		// también se puede llamar homeCelulares.search(numero, nombre) 
	}

	def clear() = {
		nombre = null
		numero = null
	}

	def eliminarCelularSeleccionado() = {
		RepoCelulares.delete(celularSeleccionado)
		this.search()
		celularSeleccionado = null
	}

}