package ar.edu.celulares.ui

import org.uqbar.arena.bindings.ObservableProperty
import org.uqbar.arena.bindings.PropertyAdapter
import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.CheckBox
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.Selector
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner

import ar.edu.celulares.domain.Celular
import ar.edu.celulares.domain.Modelo
import ar.edu.celulares.home.RepoModelos
import org.uqbar.arena.widgets.NumericField
import org.uqbar.arena.scala.ArenaScalaImplicits.closureToAction

class EditarCelularWindow(owner: WindowOwner, model: Celular) extends Dialog[Celular](owner, model) {

	override def createFormPanel(mainPanel: Panel) = {
		val form = new Panel(mainPanel)
		form.setLayout(new ColumnLayout(2))
		new Label(form).setText("Número")
		val numero = new NumericField(form)
		numero.bindValueToProperty("numero")
		numero.setWidth(150)
		
		new Label(form).setText("Nombre del cliente")
		
		val nombre = new TextBox(form)
		nombre.bindValueToProperty("nombre")
		nombre.setWidth(200)
		
		new Label(form).setText("Modelo del aparato")
		val selectorModelo = new Selector[Modelo](form)
		selectorModelo.allowNull(false)
		selectorModelo.bindValueToProperty("modeloCelular")
		val propiedadModelos = selectorModelo.bindItems(new ObservableProperty(RepoModelos, "modelos"))
		propiedadModelos.setAdapter(new PropertyAdapter(classOf[Modelo], "descripcionEntera"))
		new Label(form).setText("Recibe resumen cuenta en domicilio")
		new CheckBox(form).bindValueToProperty("recibeResumenCuenta")
	}

	override def addActions(actions: Panel) = {
		new Button(actions)
			.setCaption("Aceptar")
			.onClick({ () => this.accept })
			.setAsDefault.disableOnError

		new Button(actions) //
			.setCaption("Cancelar")
			.onClick({ () => this.cancel })
	}

}
