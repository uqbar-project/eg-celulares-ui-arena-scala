package ar.edu.celulares.home

import scala.collection.JavaConversions.asScalaBuffer
import org.uqbar.commons.model.CollectionBasedHome
import org.uqbar.commons.model.UserException
import org.uqbar.commons.utils.Observable
import ar.edu.celulares.domain.Celular
import ar.edu.celulares.domain.Modelo
import org.apache.commons.collections15.Predicate
import org.uqbar.commons.utils.ApplicationContext

object HomeCelulares extends CollectionBasedHome[Celular] {

  this.create("Laura Iturbe", 88022202, modelo("NOKIA LUMIA 625"), false)
  this.create("Julieta Passerini", 45636453, modelo("NOKIA ASHA 501"), false)
  this.create("Debora Fortini", 45610892, modelo("NOKIA ASHA 501"), true)
  this.create("Chiara Dodino", 68026976, modelo("NOKIA ASHA 501"), false)
  this.create("Melina Dodino", 40989911, modelo("LG OPTIMUS L3 II"), true)

  def modelo(modeloDescripcion: String): Modelo = {
    // TODO: ¿Implementar cake pattern?
    HomeModelos.get(modeloDescripcion)
  }

  // ********************************************************
  // ** Altas y bajas
  // ********************************************************
  def create(pNombre: String, pNumero: Integer, pModeloCelular: Modelo, pRecibeResumenCuenta: Boolean): Unit = {
    var celular = new Celular
    celular.nombre = pNombre
    celular.numero = pNumero
    celular.modeloCelular = pModeloCelular
    celular.recibeResumenCuenta = pRecibeResumenCuenta
    this.create(celular)
  }

  override def validateCreate(celular: Celular): Unit = {
    celular.validar()
    validarClientesDuplicados(celular)
  }

  def validarClientesDuplicados(celular: Celular): Unit = {
    val numero: Integer = celular.numero
    if (!this.search(numero).isEmpty) {
      throw new UserException("Ya existe un celular con el número: " + numero)
    }
  }

  // ********************************************************
  // ** Búsquedas
  // ********************************************************
  /**
   * Busca los celulares que coincidan con los datos recibidos. Tanto número como nombre pueden ser nulos,
   * en ese caso no se filtra por ese atributo.
   *
   * Soporta búsquedas por substring, por ejemplo el celular (12345, "Juan Gonzalez") será contemplado por
   * la búsqueda (23, "Gonza")
   */
  def search(numero: Integer, nombre: String = null) = {
    celulares.filter { celular => this.coincide(numero, celular.numero) && this.coincide(nombre, celular.nombre) }
  }

  def coincide(expectedValue: Any, realValue: Any): Boolean = {
    if (expectedValue == null) {
      return true
    }
    if (realValue == null) {
      return false
    }
    return realValue.toString().toLowerCase().contains(expectedValue.toString().toLowerCase())
  }

  override def getEntityType = classOf[Celular]

  override def createExample = new Celular

  override def getCriterio(example: Celular) = null

  def celulares: Seq[Celular] = allInstances

  def get(nombre: String) =
    celulares.find(_.nombre == nombre).getOrElse(null) // Acá hay que pensar algo.

  /**
   * Para el proyecto web - se mantiene la busqueda por Identificador
   */
  override def searchById(id: Int) = {
    celulares.find(_.id.equals(id)).getOrElse(null)
  }

}