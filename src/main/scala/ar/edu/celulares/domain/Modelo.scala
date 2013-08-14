package ar.edu.celulares.domain

import org.uqbar.commons.model.Entity
import org.uqbar.commons.utils.Observable

@Observable
class Modelo extends Entity {

  	var descripcion : String = _
	var costo : BigDecimal = _
	var requiereResumenCuenta : Boolean = _

	def getDescripcionEntera() : String = {
		descripcion + " ($ " + costo + ")"
	}

	override def toString() : String = {
		getDescripcionEntera
	}

}