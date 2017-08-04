package ar.edu.celulares.home

import ar.edu.celulares.domain.Celular
import ar.edu.celulares.domain.Modelo
import scala.collection.JavaConverters._
import scala.collection.JavaConversions._
import org.uqbar.commons.model.CollectionBasedRepo
import org.uqbar.commons.model.annotations.Observable

@Observable
object RepoModelos extends CollectionBasedRepo[Modelo] {

	this.create("NOKIA ASHA 501", 700f, true)
	this.create("LG OPTIMUS L5 II", 920f, false)
	this.create("LG OPTIMUS L3 II", 450f, true)
	this.create("NOKIA LUMIA 625", 350f, true)
	this.create("MOTOROLA RAZR V3", 350f, false)

	def create(descripcion: String, costo: Float, requiereResumenCuenta: Boolean) : Unit = {
		var modelo = new Modelo()
		modelo.descripcion = descripcion
		modelo.costo = costo
		modelo.requiereResumenCuenta = requiereResumenCuenta
		this.create(modelo)
	}

	def modelos: java.util.List[Modelo] = allInstances
	
	def get(descripcion: String) : Modelo =
		modelos.find(modelo => modelo.descripcion == descripcion).getOrElse(null) // Acá hay que pensar algo.

	override def getEntityType = classOf[Modelo]

	override def createExample = new Modelo

	override def getCriterio(example: Modelo) = null

}