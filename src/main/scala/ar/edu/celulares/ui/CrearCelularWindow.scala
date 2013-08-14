package ar.edu.celulares.ui

import ar.edu.celulares.domain.Celular
import org.uqbar.arena.windows.WindowOwner
import ar.edu.celulares.home.HomeCelulares

class CrearCelularWindow(owner: WindowOwner) extends EditarCelularWindow(owner, new Celular) {

	override def executeTask() = {
		HomeCelulares.create(getModelObject)
		super.executeTask()
	}

}