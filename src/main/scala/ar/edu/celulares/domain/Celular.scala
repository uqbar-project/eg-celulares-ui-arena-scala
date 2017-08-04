package ar.edu.celulares.domain

import org.uqbar.commons.model.Entity
import org.uqbar.commons.model.exceptions.UserException
import org.uqbar.commons.model.annotations.Observable
import org.uqbar.commons.model.annotations.Dependencies

@Observable
class Celular extends Entity {

  final var MAX_NUMERO = 100000

	var id : Integer = _
	var numero : Integer = _
	var nombre : String = _
	var modeloCelular : Modelo = _
	var recibeResumenCuenta : Boolean = false

	// ********************************************************
	// ** Validacion
	// ********************************************************
	/**
	 * Valida que el celular esté correctamente cargado
	 */
	def validar() : Unit = {
		if (numero == null) {
			throw new UserException("Debe ingresar número")
		}
		if (numero.intValue() <= this.MAX_NUMERO) {
			throw new UserException("El número debe ser mayor a " + this.MAX_NUMERO)
		}
		if (!this.ingresoNombre()) {
			throw new UserException("Debe ingresar nombre")
		}
		if (modeloCelular == null) {
			throw new UserException("Debe ingresar un modelo de celular")
		}
	}

	def ingresoNombre() : Boolean = {
		return (nombre != null) && (!nombre.trim().equals(""))
	}

	// ********************************************************
	// ** Getters y setters
	// ********************************************************
	@Dependencies(Array("modeloCelular"))
	def getHabilitaResumenCuenta() : Boolean = {
		return !modeloCelular.requiereResumenCuenta
	}

	// ********************************************************
	// ** Misceláneos
	// ********************************************************
	override def toString() : String = {
		val result = new StringBuffer()
		if (nombre != null) {
			result.append(nombre)  
		} else {
			result.append("Celular sin nombre")
		}
		if (modeloCelular != null) {
			result.append(" - " + modeloCelular)
		}
		if (numero != null) {
			result.append(" - " + numero)
		}
		if (recibeResumenCuenta) {
			result.append(" - recibe resumen")
		} else {
			result.append(" - no recibe resumen")
		}
		return result.toString()
	}

}