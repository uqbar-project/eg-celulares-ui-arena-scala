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

class EditarCelularWindow(owner: WindowOwner, model: Celular) extends Dialog[Celular](owner, model) {

	override def createFormPanel(mainPanel: Panel) = {
		var form = new Panel(mainPanel)
		form.setLayout(new ColumnLayout(2))
		new Label(form).setText("Número")
		new TextBox(form).bindValueToProperty("numero")
		new Label(form).setText("Nombre del cliente")
		new TextBox(form).bindValueToProperty("nombre")
		new Label(form).setText("Modelo del aparato")
		var selectorModelo = new Selector[Modelo](form)
		selectorModelo.allowNull(false)
		selectorModelo.bindValueToProperty("modeloCelular")
		var propiedadModelos = selectorModelo.bindItems(new ObservableProperty(RepoModelos, "modelos"))
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
